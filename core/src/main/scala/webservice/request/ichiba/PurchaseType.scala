package jp.co.rakuten.webservice

sealed trait PurchaseType extends Paramater {
  def int: Int
  def param = Seq("purchaseType" -> int.toString)
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
