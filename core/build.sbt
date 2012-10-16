name := "rws-scala"

organization := "org.nisshiee"

version := "0.0.1-RC2"

scalaVersion := "2.9.2"

libraryDependencies ++= Seq(
   "org.scalaz" %% "scalaz-core" % "7.0.0-M3"
  ,"org.scala-tools.time" % "time_2.9.1" % "0.5"
  ,"net.liftweb" % "lift-json_2.9.1" % "2.4"
  ,"joda-time" % "joda-time" % "2.1"
  ,"org.joda" % "joda-convert" % "1.2"
  ,"org.specs2" %% "specs2" % "1.12" % "test"
  ,"junit" % "junit" % "4.10" % "test"
  ,"org.mockito" % "mockito-all" % "1.9.0" % "test"
  ,"org.pegdown" % "pegdown" % "1.1.0" % "test"
)

publishMavenStyle := true

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com/nisshiee/rws-scala</url>
  <licenses>
    <license>
      <name>The MIT License (MIT)</name>
      <url>http://opensource.org/licenses/mit-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:nisshiee/rws-scala.git</url>
    <connection>scm:git:git@github.com:nisshiee/rws-scala.git</connection>
  </scm>
  <developers>
    <developer>
      <id>nisshiee</id>
      <name>Hirokazu Nishioka</name>
      <url>http://nisshiee.github.com/</url>
    </developer>
  </developers>)

scaladocOptions in (Compile, doc) <++= (baseDirectory in LocalProject("core")).map {
  bd => Seq("-sourcepath", bd.getAbsolutePath,
            "-doc-source-url", "https://github.com/nisshiee/rws-scala/blob/master/core€{FILE_PATH}.scala")
}

testOptions in (Test, test) += Tests.Argument("console", "html", "junitxml")

initialCommands := """
import org.scala_tools.time.Imports._
"""
