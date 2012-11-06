package rwsscala.ichiba

sealed trait PamphletFlag

object PamphletFlag {

  case object All extends PamphletFlag
  case object OnlyAccept extends PamphletFlag
}
