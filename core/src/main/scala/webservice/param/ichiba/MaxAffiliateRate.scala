package jp.co.rakuten.webservice.param.ichiba

import jp.co.rakuten.webservice.util._
import scalaz._, Scalaz._

sealed trait MaxAffiliateRate extends Parameter

object MaxAffiliateRate {

  private case class On(value: Int) extends MaxAffiliateRate {

    def param = Seq("maxAffiliateRate" -> TenthPlace.int2str(value))
  }

  case object Off extends MaxAffiliateRate {

    def param = Seq()
  }

  def apply(value: Double): MaxAffiliateRate = TenthPlace.double2int(value) match {
    case i if i < 10 => On(10)
    case i if i > 999 => On(999)
    case i => On(i)
  }

  def unapply(m: MaxAffiliateRate): Option[Double] = m match {
    case On(i) => TenthPlace.int2double(i).some
    case Off => none
  }
}

trait MaxAffiliateRates {
  implicit def double2maxAffiliateRate(value: Double): MaxAffiliateRate = MaxAffiliateRate(value)
  implicit def float2maxAffiliateRate(value: Float): MaxAffiliateRate = MaxAffiliateRate(value)
  implicit def doubleOpt2maxAffiliateRate(opt: Option[Double]): MaxAffiliateRate =
    opt ∘ double2maxAffiliateRate | MaxAffiliateRate.Off
  implicit def floatOpt2maxAffiliateRate(opt: Option[Float]): MaxAffiliateRate =
    opt ∘ float2maxAffiliateRate | MaxAffiliateRate.Off
}
