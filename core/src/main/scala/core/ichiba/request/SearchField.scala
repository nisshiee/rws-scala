package rwsscala.ichiba

sealed trait SearchField

object SearchField {

  case object Broad extends SearchField
  case object Strict extends SearchField
}
