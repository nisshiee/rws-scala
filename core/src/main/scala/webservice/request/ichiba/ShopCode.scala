package jp.co.rakuten.webservice

case class ShopCode(opt: Option[String]) extends Parameter {
  def param = opt.toSeq map { "shopCode" -> _ }
}

trait ShopCodes {

  implicit def str2shopCode(value: String): ShopCode = ShopCode(Some(value))
}
