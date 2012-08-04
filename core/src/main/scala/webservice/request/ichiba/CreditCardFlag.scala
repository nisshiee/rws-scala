package jp.co.rakuten.webservice

sealed trait CreditCardFlag extends Paramater {
  def int: Int
  def param = Seq("creditCardFlag" -> int.toString)
}
case object CreditCardAll extends CreditCardFlag {
  val int = 0
}
case object OnlyAllowCreditCard extends CreditCardFlag {
  val int = 1
}
