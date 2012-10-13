package rwsscala

import rwsscala.util._

case class ApplicationId(value: String) extends Parameter {
  def param = Seq("applicationId" -> value)
}

trait ApplicationIds {
  implicit def str2applicationId(value: String): ApplicationId = ApplicationId(value)
}
