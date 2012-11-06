package rwsscala.ichiba

import scalaz._, Scalaz._

sealed trait ShopCode

object ShopCode {

  case object Off extends ShopCode

  private case class On(value: String) extends ShopCode

  def apply(value: String): ShopCode = On(value)

  def unapply(s: ShopCode): Option[String] = s match {
    case On(v) => v.some
    case Off => none
  }
}

trait ShopCodes {

  implicit def str2shopCode(value: String): ShopCode = ShopCode(value)
  implicit def strOpt2shopCode(opt: Option[String]): ShopCode =
    opt ∘ str2shopCode | ShopCode.Off
}
