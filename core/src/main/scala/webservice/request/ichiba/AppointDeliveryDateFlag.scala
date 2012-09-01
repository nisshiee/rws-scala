package jp.co.rakuten.webservice

sealed trait AppointDeliveryDateFlag extends Parameter {
  def int: Int
  def param = Seq("appointDeliveryDateFlag" -> int.toString)
}
case object AppointDeliveryDateAll extends AppointDeliveryDateFlag {
  val int = 0
}
case object OnlyAllowAppointDeliveryDate extends AppointDeliveryDateFlag {
  val int = 1
}
