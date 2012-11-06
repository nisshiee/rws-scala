package rwsscala.ichiba

sealed trait GiftFlag

object GiftFlag {

  case object All extends GiftFlag
  case object OnlyAccept extends GiftFlag
}
