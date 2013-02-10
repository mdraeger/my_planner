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
    // where to search snippet
    LiftRules.addToPackages("org.draegisoft.training_planner")

    // Build SiteMap
    def sitemap(): SiteMap = SiteMap(
      Menu.i("Home") / "index"
      )

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r:
      Req) => new
    Html5Properties(r.userAgent))
}
}
