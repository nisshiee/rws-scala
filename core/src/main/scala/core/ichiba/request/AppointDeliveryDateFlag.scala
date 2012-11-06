package rwsscala.ichiba

import rwsscala.util._

sealed trait AppointDeliveryDateFlag {

  import AppointDeliveryDateFlag._

  def int: Int
  def param = this match {
    case All => Seq()
    case a => Seq("appointDeliveryDateFlag" -> a.int.toString)
  }
}

object AppointDeliveryDateFlag {

  case object All extends AppointDeliveryDateFlag {
    val int = 0
  }
  case object OnlyAccept extends AppointDeliveryDateFlag {
    val int = 1
  }
}
