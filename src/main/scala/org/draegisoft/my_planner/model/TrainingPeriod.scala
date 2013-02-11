package org.draegisoft.my_planner.model

import net.liftweb.record.{MetaRecord, Record}
import net.liftweb.record.field._
import net.liftweb.squerylrecord.KeyedRecord
import org.squeryl.annotations.Column

class TrainingPeriod private() extends Record[TrainingPeriod] with KeyedRecord[Long] {
  
  override def meta = TrainingPeriod
  
  @Column(name="id")
  override val idField = new LongField(this)
  
  val begin_date = new DateTimeField(this)
  val end_date = new DateTimeField(this)
  val trainingId = new LongField(this)

  lazy val training = PlannerSchema.trainingToTrainingPeriod.right(this)
}

object TrainingPeriod extends TrainingPeriod with MetaRecord[TrainingPeriod]
