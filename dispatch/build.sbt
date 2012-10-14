name := "rws-scala-dispatch"

organization := "org.nisshiee"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.9.2"

libraryDependencies ++= Seq(
   "net.databinder.dispatch" %% "core" % "0.9.0"
  ,"org.specs2" %% "specs2" % "1.12" % "test"
  ,"org.mockito" % "mockito-all" % "1.9.0" % "test"
  ,"org.pegdown" % "pegdown" % "1.1.0" % "test"
)

testOptions in (Test, test) += Tests.Argument("console", "html", "junitxml")

initialCommands := """
import org.scala_tools.time.Imports._
import rwsscala.dispatch._
"""
