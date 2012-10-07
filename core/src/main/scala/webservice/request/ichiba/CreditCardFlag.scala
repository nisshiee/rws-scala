package jp.co.rakuten.webservice

import util._

sealed trait CreditCardFlag extends Parameter {
  def int: Int
  def param = this match {
    case CreditCardAll => Seq()
    case c => Seq("creditCardFlag" -> c.int.toString)
  }
}
case object CreditCardAll extends CreditCardFlag {
  val int = 0
}
case object OnlyAllowCreditCard extends CreditCardFlag {
  val int = 1
}
