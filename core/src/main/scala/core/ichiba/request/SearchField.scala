package rwsscala.ichiba

import rwsscala.util._

sealed trait SearchField extends Parameter {

  import SearchField._

  def int: Int
  def param = this match {
    case Strict => Seq()
    case f => Seq("field" -> f.int.toString)
  }
}

object SearchField {

  case object Broad extends SearchField {
    val int = 0
  }
  case object Strict extends SearchField {
    val int = 1
  }
}
