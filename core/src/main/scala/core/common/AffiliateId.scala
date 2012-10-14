package rwsscala

import rwsscala.util._

case class AffiliateId(opt: Option[String]) extends Parameter {
  def param = opt.toSeq map { "affiliateId" -> _ }
}

trait AffiliateIds {

  implicit def str2affiliateId(value: String): AffiliateId = AffiliateId(Some(value))
  implicit def strOpt2affiliateId(opt: Option[String]): AffiliateId = AffiliateId(opt)
}
