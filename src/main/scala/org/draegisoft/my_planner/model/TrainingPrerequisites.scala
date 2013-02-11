package org.draegisoft.my_planner.model

import net.liftweb.record.{MetaRecord, Record}
import net.liftweb.record.field.LongField
import net.liftweb.squerylrecord.KeyedRecord
import org.squeryl.annotations.Column

class TrainingPrerequisites private() extends Record[TrainingPrerequisites] with KeyedRecord[Long] {
  
  override def meta = TrainingPrerequisites

  @Column(name="id")
  override val idField = new LongField(this)

  val trainingId = new LongField(this)
  val trainingRequires = new LongField(this)
}

object TrainingPrerequisites extends TrainingPrerequisites with MetaRecord[TrainingPrerequisites]
