organization := "org.draegisoft"

name := "training_planner"

version := "0.1-SNAPSHOT"

scalaVersion := "2.10.0"

seq(com.github.siasia.WebPlugin.webSettings :_*)

libraryDependencies ++= {
  val liftVersion = "2.5-M4"
  Seq(
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile",
    "org.eclipse.jetty" % "jetty-webapp" % "8.0.4.v20111024" %
"container,test",
    "net.liftweb" %% "lift-squeryl-record" % liftVersion
  )
}
