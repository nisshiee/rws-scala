package rwsscala.ichiba

import rwsscala.util._

sealed trait PurchaseType extends Parameter {

  import PurchaseType._

  def int: Int
  def param = this match {
    case NormalOrder => Seq()
    case _ => Seq("purchaseType" -> int.toString)
  }
}

object PurchaseType {

  case object NormalOrder extends PurchaseType {
    val int = 0
  }
  case object StandingOrder extends PurchaseType {
    val int = 1
  }
  case object DistributeOrder extends PurchaseType {
    val int = 2
  }
}
