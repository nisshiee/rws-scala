import sbt._
import Keys._

object RwsScalaRootBuild extends Build {

  lazy val core = Project(
     id = "core"
    ,base = file("core")
  )

  lazy val dispatch = Project(
     id = "dispatch"
    ,base = file("dispatch")
  ) dependsOn core

  lazy val scalaz = Project(
     id = "scalaz"
    ,base = file("scalaz")
  ) dependsOn core

  lazy val sample = Project(
     id = "sample"
    ,base = file("sample")
  ) dependsOn dispatch
}
