package jp.co.rakuten.webservice

import util._
import scalaz._, Scalaz._

sealed trait Carrier extends Parameter {
  def int: Int
  def param = this match {
    case PC => Seq()
    case a => Seq("carrier" -> a.int.toString)
  }
}
case object PC extends Carrier {
  val int = 0
}
case object Mobile extends Carrier {
  val int = 1
}
case object SP extends Carrier {
  val int = 2
}

object Carrier {

  val parseVld: Int => Validation[ApiError, Carrier] = {
    case 0 => PC.success
    case 1 => Mobile.success
    case 2 => SP.success
    case _ => JsonParseError("carrier must be in 0, 1, 2").failure
  }
}
