package jp.co.rakuten.webservice

import util._
import scalaz._, Scalaz._

sealed trait MinPrice extends Parameter

object MinPrice {

  private case class On(value: Long) extends MinPrice {

    def param = Seq("minPrice" -> value.toString)
  }

  case object Off extends MinPrice {

    def param = Seq()
  }

  def apply(value: Long): MinPrice = value match {
    case v if v < 0L => On(0L)
    case _ => On(value)
  }

  def unapply(m: MinPrice): Option[Long] = m match {
    case On(v) => v.some
    case Off => none
  }
}

trait MinPrices {
  implicit def long2minPrice(value: Long): MinPrice = MinPrice(value)
  implicit def int2minPrice(value: Int): MinPrice = long2minPrice(value)
  implicit def longOpt2minPrice(opt: Option[Long]): MinPrice = opt ∘ long2minPrice | MinPrice.Off
  implicit def intOpt2minPrice(opt: Option[Int]): MinPrice = opt ∘ int2minPrice | MinPrice.Off
}
