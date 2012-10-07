package jp.co.rakuten.webservice

import util.TenthPlace
import scalaz._, Scalaz._

sealed trait MinAffiliateRate extends Parameter

object MinAffiliateRate {

  private case class On(value: Int) extends MinAffiliateRate {

    def param = Seq("minAffiliateRate" -> TenthPlace.int2str(value))
  }

  case object Off extends MinAffiliateRate {

    def param = Seq()
  }

  def apply(value: Double): MinAffiliateRate = TenthPlace.double2int(value) match {
    case i if i < 10 => On(10)
    case i if i > 999 => On(999)
    case i => On(i)
  }

  def unapply(m: MinAffiliateRate): Option[Double] = m match {
    case On(i) => TenthPlace.int2double(i).some
    case Off => none
  }
}

trait MinAffiliateRates {
  implicit def double2minAffiliateRate(value: Double): MinAffiliateRate = MinAffiliateRate(value)
  implicit def float2minAffiliateRate(value: Float): MinAffiliateRate = MinAffiliateRate(value)
  implicit def doubleOpt2minAffiliateRate(opt: Option[Double]): MinAffiliateRate =
    opt ∘ double2minAffiliateRate | MinAffiliateRate.Off
  implicit def floatOpt2minAffiliateRate(opt: Option[Float]): MinAffiliateRate =
    opt ∘ float2minAffiliateRate | MinAffiliateRate.Off
}
