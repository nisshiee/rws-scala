package jp.co.rakuten.webservice

case class MaxAffiliateRate(opt: Option[Int]) extends Paramater {
  def param = opt.toSeq map { "maxAffiliateRate" -> _.toString }
}

trait MaxAffiliateRates {
  implicit def int2maxAffiliateRate(value: Int): MaxAffiliateRate = MaxAffiliateRate(Some(value))
}
