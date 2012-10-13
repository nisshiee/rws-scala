package rwsscala.ichiba

import rwsscala.util._

sealed trait Availability extends Parameter {

  import Availability._

  def int: Int
  def param = this match {
    case OnlyAvailable => Seq()
    case a => Seq("availability" -> a.int.toString)
  }
}

object Availability {

  case object OnlyAvailable extends Availability {
    val int = 1
  }
  case object All extends Availability {
    val int = 0
  }
}
