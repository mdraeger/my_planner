package org.draegisoft.my_planner.model

import net.liftweb.record.{MetaRecord, Record}
import net.liftweb.record.field._
import net.liftweb.squerylrecord.KeyedRecord
import org.squeryl.annotations.Column
import org.squeryl.dsl.ManyToMany

class Training private() extends Record[Training] with KeyedRecord[Long] {
  
  override def meta = Training
  
  @Column(name="id")
  override val idField = new LongField(this)
  
  val name = new StringField(this, "")
  val trainingId = new LongField(this, 0)

  lazy val trainingPeriods = PlannerSchema.trainingToTrainingPeriod.left(this)
  lazy val trainingRequires: ManyToMany[Training, TrainingPrerequisites] = PlannerSchema.trainingRequirements.right(this)
}
object Training extends Training with MetaRecord[Training]
