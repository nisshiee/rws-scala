package jp.co.rakuten.webservice

import util._
import scalaz._, Scalaz._

sealed trait ShopCode extends Parameter

object ShopCode {

  case object Off extends ShopCode {

    def param = Seq()
  }

  private case class On(value: String) extends ShopCode {

    def param = Seq("shopCode" -> value)
  }

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
