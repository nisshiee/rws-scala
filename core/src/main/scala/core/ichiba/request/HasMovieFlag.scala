package rwsscala.ichiba

import rwsscala.util._

sealed trait HasMovieFlag extends Parameter {
  def int: Int
  def param = this match {
    case HasMovieAll => Seq()
    case _ => Seq("hasMovieFlag" -> int.toString)
  }
}
case object HasMovieAll extends HasMovieFlag {
  val int = 0
}
case object OnlyHasMovie extends HasMovieFlag {
  val int = 1
}
