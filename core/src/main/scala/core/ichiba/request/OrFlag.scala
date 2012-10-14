package rwsscala.ichiba

import rwsscala.util._

sealed trait OrFlag extends Parameter {

  import OrFlag._

  def int: Int
  def param = this match {
    case And => Seq()
    case _ => Seq("orFlag" -> int.toString)
  }
}

object OrFlag {

  case object And extends OrFlag {
    val int = 0
  }
  case object Or extends OrFlag {
    val int = 1
  }
}
