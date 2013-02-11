package org.draegisoft.my_planner.model

import org.squeryl.Schema
import org.squeryl.dsl.ManyToMany

import net.liftweb.record.{MetaRecord, Record}
import net.liftweb.record.field.{StringField, LongField}
import net.liftweb.squerylrecord.KeyedRecord
import net.liftweb.squerylrecord.RecordTypeMode._

object PlannerSchema extends Schema {
  val persons = table[Person]
  val timesNotAvailable = table[TimesNotAvailable]

  val personToTimesNotAvailable = oneToManyRelation(persons, timesNotAvailable) via ((p,t) => p.id === t.personId)  

  val trainings = table[Training]
  val trainingPeriods = table[TrainingPeriod]
  val trainingToTrainingPeriod = oneToManyRelation(trainings, trainingPeriods) via ((t, tp) => t.id === tp.trainingId)
  val trainingRequirements = manyToManyRelation(trainings, trainings).via[TrainingPrerequisites] {(t1, t2, tlink) =>
    (t1.id === tlink.trainingId, tlink.trainingRequires === t2.id)}
  
}
