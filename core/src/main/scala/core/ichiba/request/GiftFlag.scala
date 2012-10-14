package rwsscala.ichiba

import rwsscala.util._

sealed trait GiftFlag extends Parameter {

  import GiftFlag._

  def int: Int
  def param = this match {
    case All => Seq()
    case g => Seq("giftFlag" -> g.int.toString)
  }
}

object GiftFlag {

  case object All extends GiftFlag {
    val int = 0
  }
  case object OnlyAccept extends GiftFlag {
    val int = 1
  }
}
