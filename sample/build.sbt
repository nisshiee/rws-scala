name := "rws-scala-sample"

organization := "nisshiee.org"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.9.2"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
   "org.specs2" %% "specs2" % "1.11" % "test"
  ,"org.mockito" % "mockito-all" % "1.9.0" % "test"
  ,"junit" % "junit" % "4.10" % "test"
  ,"org.pegdown" % "pegdown" % "1.1.0" % "test"
)

testOptions in (Test, test) += Tests.Argument("console", "html", "junitxml")

initialCommands := """
import dispatch._
import rwsscala._, Implicits._
import rwsscala.ichiba._
import rwsscala.dispatch._
implicit val https = DispatchHttps
"""

cleanupCommands := """
Http.shutdown
"""
