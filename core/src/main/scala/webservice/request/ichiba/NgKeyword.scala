package jp.co.rakuten.webservice

case class NgKeyword(opt: Option[String]) extends Paramater {
  def param = opt.toSeq map { "NGKeyword" -> _ }
}

trait NgKeywords {

  implicit def str2ngKeyword(value: String): NgKeyword = NgKeyword(Some(value))
}

