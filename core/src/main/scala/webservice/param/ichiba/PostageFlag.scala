package rwsscala.param.ichiba

import rwsscala.util._

sealed trait PostageFlag extends Parameter {
  def int: Int
  def param = this match {
    case PostageAll => Seq()
    case _ => Seq("postageFlag" -> int.toString)
  }
}
case object PostageAll extends PostageFlag {
  val int = 0
}
case object OnlyFreePostage extends PostageFlag {
  val int = 1
}
