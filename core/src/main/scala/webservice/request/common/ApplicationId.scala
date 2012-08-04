package jp.co.rakuten.webservice

case class ApplicationId(value: String) extends Paramater {
  def param = Seq("applicationId" -> value)
}

trait ApplicationIds {
  implicit def str2applicationId(value: String): ApplicationId = ApplicationId(value)
}
