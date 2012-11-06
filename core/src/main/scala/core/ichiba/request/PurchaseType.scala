package rwsscala.ichiba

sealed trait PurchaseType

object PurchaseType {

  case object NormalOrder extends PurchaseType
  case object StandingOrder extends PurchaseType
  case object DistributeOrder extends PurchaseType
}
