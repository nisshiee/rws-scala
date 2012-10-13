package rwsscala.ichiba

import rwsscala.util._

sealed trait OrFlag extends Parameter {
  def int: Int
  def param = this match {
    case AndSearch => Seq()
    case _ => Seq("orFlag" -> int.toString)
  }
}
case object AndSearch extends OrFlag {
  val int = 0
}
case object OrSearch extends OrFlag {
  val int = 1
}
