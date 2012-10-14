name := "rws-scala-sample"

organization := "nisshiee.org"

version := "0.0.1-RC1"

scalaVersion := "2.9.2"

libraryDependencies ++= Seq(
   "org.specs2" %% "specs2" % "1.11" % "test"
  ,"org.mockito" % "mockito-all" % "1.9.0" % "test"
  ,"junit" % "junit" % "4.10" % "test"
  ,"org.pegdown" % "pegdown" % "1.1.0" % "test"
)

testOptions in (Test, test) += Tests.Argument("console", "html", "junitxml")

initialCommands := """
import dispatch.Http
import rwsscala._, rwsscala.ichiba._
implicit val https = rwsscala.dispatch.DispatchHttps
"""

cleanupCommands := """
Http.shutdown
"""
