package org.draegisoft.my_planner.snippet

import scala.xml.{NodeSeq, Text}
import net.liftweb.util._
import net.liftweb.common._
import net.liftweb.util.Helpers._

import net.liftweb.squerylrecord.RecordTypeMode._
import org.draegisoft.my_planner.model.PlannerSchema

class TrainingPrerequisitesSnippet {
  val trainings = from(PlannerSchema.trainings)(t => select(t))

  def render = "#training-ids *" #> trainings.map { training =>
      ".training_name *" #> training.name.is &
      ".training_requires *" #> training.trainingRequires.map(_.name.is)
    }
}

