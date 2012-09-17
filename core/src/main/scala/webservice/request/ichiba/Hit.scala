package jp.co.rakuten.webservice

import scalaz._, Scalaz._

sealed trait Hit extends Parameter {
  def value: Int
}

object Hit {

  private case class Impl(value: Int) extends Hit {

    def param = value match {
      case 30 => Seq()
      case v => Seq("hits" -> v.toString)
    }
  }

  def apply(value: Int): Hit = value match {
    case v if v < 1 => Impl(1)
    case v if v > 30 => Impl(30)
    case v => Impl(v)
  }

  def unapply(h: Hit): Option[Int] = h match {
    case Impl(v) => v.some
  }
}

trait Hits {
  implicit def int2hit(value: Int): Hit = Hit(value)
}
