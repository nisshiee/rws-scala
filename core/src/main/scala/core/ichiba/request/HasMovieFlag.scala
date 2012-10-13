package rwsscala.ichiba

import rwsscala.util._

sealed trait HasMovieFlag extends Parameter {

  import HasMovieFlag._

  def int: Int
  def param = this match {
    case All => Seq()
    case _ => Seq("hasMovieFlag" -> int.toString)
  }
}

object HasMovieFlag {

  case object All extends HasMovieFlag {
    val int = 0
  }
  case object OnlyHave extends HasMovieFlag {
    val int = 1
  }
}
