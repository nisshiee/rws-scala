package rwsscala.ichiba

import scalaz._, Scalaz._

sealed trait PointRate

object PointRate {

  case object Off extends PointRate
  sealed trait On extends PointRate
  case object AnyRate extends On
  private case class GivenRate(value: Int) extends On

  def apply(value: Int): PointRate = On(value)

  def unapply(p: PointRate): Option[Int] = p match {
    case o: On => On.unapply(o)
    case _ => none
  }

  object On {

    def apply(value: Int): PointRate = value match {
      case v if v < 2 => GivenRate(2)
      case v if v > 10 => GivenRate(10)
      case _ => GivenRate(value)
    }

    def unapply(o: On): Option[Int] = o match {
      case GivenRate(v) => v.some
      case _ => none
    }
  }
}

trait PointRates {

  implicit def int2pointRate(value: Int): PointRate = PointRate(value)
  implicit def intOpt2pointRate(opt: Option[Int]): PointRate =
    opt ∘ int2pointRate | PointRate.AnyRate
}
