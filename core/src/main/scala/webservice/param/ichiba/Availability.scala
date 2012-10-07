package jp.co.rakuten.webservice.param.ichiba

import jp.co.rakuten.webservice.util._

sealed trait Availability extends Parameter {
  def int: Int
  def param = this match {
    case Available => Seq()
    case a => Seq("availability" -> a.int.toString)
  }
}
case object Available extends Availability {
  val int = 1
}
case object All extends Availability {
  val int = 0
}
