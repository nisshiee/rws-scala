package rwsscala.ichiba

sealed trait CreditCardFlag

object CreditCardFlag {

  case object All extends CreditCardFlag
  case object OnlyAccept extends CreditCardFlag
}
