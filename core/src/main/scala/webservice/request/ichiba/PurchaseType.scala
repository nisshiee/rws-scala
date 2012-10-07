package jp.co.rakuten.webservice

import util._

sealed trait PurchaseType extends Parameter {
  def int: Int
  def param = this match {
    case NormalOrder => Seq()
    case _ => Seq("purchaseType" -> int.toString)
  }
}
case object NormalOrder extends PurchaseType {
  val int = 0
}
case object StandingOrder extends PurchaseType {
  val int = 1
}
case object DistributeOrder extends PurchaseType {
  val int = 2
}
