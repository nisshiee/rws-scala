package jp.co.rakuten.webservice.param

import jp.co.rakuten.webservice.util._

case class AffiliateId(opt: Option[String]) extends Parameter {
  def param = opt.toSeq map { "affiliateId" -> _ }
}

trait AffiliateIds {

  implicit def str2affiliateId(value: String): AffiliateId = AffiliateId(Some(value))
}
