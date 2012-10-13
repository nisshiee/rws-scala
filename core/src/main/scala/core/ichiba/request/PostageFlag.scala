package rwsscala.ichiba

import rwsscala.util._

sealed trait PostageFlag extends Parameter {

  import PostageFlag._

  def int: Int
  def param = this match {
    case All => Seq()
    case _ => Seq("postageFlag" -> int.toString)
  }
}

object PostageFlag {

  case object All extends PostageFlag {
    val int = 0
  }
  case object OnlyInclude extends PostageFlag {
    val int = 1
  }
}
