name := "rws-scala"

organization := "jp.co.rakuten.webservice"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.9.2"

resolvers ++= Seq(
   "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
  ,"Sonatype releases" at "http://oss.sonatype.org/content/repositories/releases/"
)

libraryDependencies ++= Seq(
   "net.databinder.dispatch" %% "core" % "0.9.0"
  ,"com.typesafe.config" % "config" % "0.3.0"
  ,"org.scala-tools.time" % "time_2.9.1" % "0.5"
  ,"joda-time" % "joda-time" % "2.1"
  ,"org.joda" % "joda-convert" % "1.2"
  ,"org.specs2" %% "specs2" % "1.11" % "test"
  ,"org.mockito" % "mockito-all" % "1.9.0" % "test"
  ,"junit" % "junit" % "4.10" % "test"
  ,"org.pegdown" % "pegdown" % "1.1.0" % "test"
)

testOptions in (Test, test) += Tests.Argument("console", "html", "junitxml")

initialCommands := """
import org.scala_tools.time.Imports._
"""
