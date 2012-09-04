package jp.co.rakuten.webservice

sealed trait AppointDeliveryDateFlag extends Parameter {
  def int: Int
  def param = this match {
    case AppointDeliveryDateAll => Seq()
    case a => Seq("appointDeliveryDateFlag" -> a.int.toString)
  }
}
case object AppointDeliveryDateAll extends AppointDeliveryDateFlag {
  val int = 0
}
case object OnlyAllowAppointDeliveryDate extends AppointDeliveryDateFlag {
  val int = 1
}
