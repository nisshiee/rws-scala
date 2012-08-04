package jp.co.rakuten.webservice

sealed trait HasMovieFlag extends Paramater {
  def int: Int
  def param = Seq("hasMovieFlag" -> int.toString)
}
case object HasMovieAll extends HasMovieFlag {
  val int = 0
}
case object OnlyHasMovie extends HasMovieFlag {
  val int = 1
}
