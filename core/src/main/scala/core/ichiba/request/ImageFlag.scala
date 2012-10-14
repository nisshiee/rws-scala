package rwsscala.ichiba

import rwsscala.util._

sealed trait ImageFlag extends Parameter {

  import ImageFlag._

  def int: Int
  def param = this match {
    case All => Seq()
    case _ => Seq("imageFlag" -> int.toString)
  }
}

object ImageFlag {

  case object All extends ImageFlag {
    val int = 0
  }
  case object OnlyHave extends ImageFlag {
    val int = 1
  }
}
