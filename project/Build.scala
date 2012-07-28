import sbt._
import Keys._

object RwsScalaRootBuild extends Build {

  lazy val core = Project(
     id = "core"
    ,base = file("core")
  )

  lazy val sample = Project(
     id = "sample"
    ,base = file("sample")
  ) dependsOn core
}
