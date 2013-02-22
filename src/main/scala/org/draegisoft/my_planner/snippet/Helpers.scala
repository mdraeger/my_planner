package org.draegisoft.my_planner.snippet

import scala.xml.{NodeSeq,Text}
import net.liftweb.util.BindHelpers._

import java.text.DateFormat
import java.util.{Date, Locale}

object Helpers {
  
  def currentDate = {
    val df = DateFormat.getDateInstance(DateFormat.FULL, Locale.GERMANY)
    "#current_date" #> <p>{(df.format(new Date)).toString}</p>
  }
}
