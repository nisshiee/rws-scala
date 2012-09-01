package jp.co.rakuten.webservice

case class MinAffiliateRate(opt: Option[Int]) extends Parameter {
  def param = opt.toSeq map { "minAffiliateRate" -> _.toString }
}

trait MinAffiliateRates {
  implicit def int2minAffiliateRate(value: Int): MinAffiliateRate = MinAffiliateRate(Some(value))
}
