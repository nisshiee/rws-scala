package rwsscala.param.ichiba

import rwsscala.util._
import scalaz._, Scalaz._

sealed trait MaxPrice extends Parameter

object MaxPrice {

  private case class On(value: Long) extends MaxPrice {

    def param = Seq("maxPrice" -> value.toString)
  }

  case object Off extends MaxPrice {

    def param = Seq()
  }

  def apply(value: Long): MaxPrice = value match {
    case v if v < 0L => On(0L)
    case _ => On(value)
  }

  def unapply(m: MaxPrice): Option[Long] = m match {
    case On(v) => v.some
    case Off => none
  }
}

trait MaxPrices {
  implicit def long2maxPrice(value: Long): MaxPrice = MaxPrice(value)
  implicit def int2maxPrice(value: Int): MaxPrice = long2maxPrice(value)
  implicit def longOpt2maxPrice(opt: Option[Long]): MaxPrice = opt ∘ long2maxPrice | MaxPrice.Off
  implicit def intOpt2maxPrice(opt: Option[Int]): MaxPrice = opt ∘ int2maxPrice | MaxPrice.Off
}
