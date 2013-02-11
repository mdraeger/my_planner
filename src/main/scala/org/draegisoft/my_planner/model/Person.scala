package org.draegisoft.my_planner.model

import net.liftweb.record.{MetaRecord, Record}
import net.liftweb.record.field._
import net.liftweb.squerylrecord.KeyedRecord
import org.squeryl.annotations.Column

class Person private() extends Record[Person] with KeyedRecord[Long] {
  
  override def meta = Person
  
  @Column(name="id")
  override val idField = new LongField(this)
  
  val first_name = new StringField(this, "")
  val last_name = new StringField(this, "")
  val sap_id = new LongField(this)
  val email = new EmailField(this, 70)

  lazy val timesNotAvailable = PlannerSchema.personToTimesNotAvailable.left(this)
}
object Person extends Person with MetaRecord[Person]
