package org.draegisoft.my_planner.model

import net.liftweb.record.{MetaRecord, Record}
import net.liftweb.record.field._
import net.liftweb.squerylrecord.KeyedRecord
import org.squeryl.annotations.Column

class TimesNotAvailable private() extends Record[TimesNotAvailable] with KeyedRecord[Long] {
  
  override def meta = TimesNotAvailable
  
  @Column(name="id")
  override val idField = new LongField(this)
  
  val personId = new LongField(this)
  val begin_date = new DateTimeField(this)
  val end_date = new DateTimeField(this)

  lazy val person = PlannerSchema.personToTimesNotAvailable.right(this)
}
object TimesNotAvailable extends TimesNotAvailable with MetaRecord[TimesNotAvailable]
