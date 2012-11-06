package rwsscala.ichiba

sealed trait HasMovieFlag

object HasMovieFlag {

  case object All extends HasMovieFlag
  case object OnlyHave extends HasMovieFlag
}
