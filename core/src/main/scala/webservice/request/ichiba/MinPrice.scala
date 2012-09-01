package jp.co.rakuten.webservice

case class MinPrice(opt: Option[Long]) extends Paramater {
  def param = opt.toSeq map { "minPrice" -> _.toString }
}

trait MinPrices {
  implicit def long2minPrice(value: Long): MinPrice = MinPrice(Some(value))
  implicit def int2minPrice(value: Int): MinPrice = long2minPrice(value)
}
