package bootstrap.liftweb

import net.liftweb.http._
import net.liftweb.sitemap.{Menu, SiteMap}
import net.liftweb.squerylrecord.SquerylRecord
import net.liftweb.squerylrecord.RecordTypeMode._
import net.liftweb.util.LoanWrapper

import org.squeryl.Session
import org.squeryl.adapters.PostgreSqlAdapter

import java.sql._

/**
* A class that's instantiated early and run. It allows the application
* to modify lift's environment
*/
class Boot {
  Class.forName("org.postgresql.Driver")

  def connection = DriverManager.getConnection(
    "jdbc:postgresql://localhost/training_planner",
    "postgres", "postgres")

  def boot {
    SquerylRecord.initWithSquerylSession(
      Session.create(connection, new PostgreSqlAdapter))

    S.addAround(new LoanWrapper {
        override def apply[T](f: => T): T = inTransaction { f }
    })

    setUpDB

    // where to search snippet
    LiftRules.addToPackages("org.draegisoft.my_planner")

    // Build SiteMap
    def sitemap(): SiteMap = SiteMap(
      Menu.i("Home") / "index"
      )

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r:
      Req) => new
    Html5Properties(r.userAgent))
}

  def setUpDB = {
    inTransaction {
      import org.draegisoft.my_planner.model._
      import PlannerSchema._
      printDdl
      drop
      create

      // insert some trainings
      val t1 = trainings.insert(Training.createRecord.name("t1"))
      val t2 = trainings.insert(Training.createRecord.name("t2"))
      val t3 = trainings.insert(Training.createRecord.name("t3"))
      val t4 = trainings.insert(Training.createRecord.name("t4"))
      val t5 = trainings.insert(Training.createRecord.name("t5"))
      // associate some of the trainings
      t2.trainingRequires.associate(t1)  
      t3.trainingRequires.associate(t1)  
      t5.trainingRequires.associate(t3)  
      t5.trainingRequires.associate(t4)  
    }
  }
}
