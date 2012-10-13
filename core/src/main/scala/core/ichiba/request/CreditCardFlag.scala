package rwsscala.ichiba

import rwsscala.util._

sealed trait CreditCardFlag extends Parameter {

  import CreditCardFlag._

  def int: Int
  def param = this match {
    case All => Seq()
    case c => Seq("creditCardFlag" -> c.int.toString)
  }
}

object CreditCardFlag {

  case object All extends CreditCardFlag {
    val int = 0
  }
  case object OnlyAccept extends CreditCardFlag {
    val int = 1
  }
}
