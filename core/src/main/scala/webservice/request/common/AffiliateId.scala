package jp.co.rakuten.webservice

case class AffiliateId(opt: Option[String]) extends Paramater {
  def param = opt.toSeq map { "affiliateId" -> _ }
}

trait AffiliateIds {

  implicit def str2affiliateId(value: String): AffiliateId = AffiliateId(Some(value))
}
