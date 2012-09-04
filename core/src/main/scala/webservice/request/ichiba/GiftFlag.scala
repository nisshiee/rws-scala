package jp.co.rakuten.webservice

sealed trait GiftFlag extends Parameter {
  def int: Int
  def param = this match {
    case GiftAll => Seq()
    case g => Seq("giftFlag" -> g.int.toString)
  }
}
case object GiftAll extends GiftFlag {
  val int = 0
}
case object OnlyAllowGift extends GiftFlag {
  val int = 1
}
