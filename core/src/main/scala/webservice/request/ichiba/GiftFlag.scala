package jp.co.rakuten.webservice

sealed trait GiftFlag extends Parameter {
  def int: Int
  def param = Seq("giftFlag" -> int.toString)
}
case object GiftAll extends GiftFlag {
  val int = 0
}
case object OnlyAllowGift extends GiftFlag {
  val int = 1
}
