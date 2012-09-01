package jp.co.rakuten.webservice

case class MaxPrice(opt: Option[Long]) extends Paramater {
  def param = opt.toSeq map { "maxPrice" -> _.toString }
}

trait MaxPrices {
  implicit def long2maxPrice(value: Long): MaxPrice = MaxPrice(Some(value))
  implicit def int2maxPrice(value: Int): MaxPrice = long2maxPrice(value)
}
