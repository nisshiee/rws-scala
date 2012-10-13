package rwsscala.ichiba

import rwsscala.util._

sealed trait PamphletFlag extends Parameter {

  import PamphletFlag._

  def int: Int
  def param = this match {
    case All => Seq()
    case _ => Seq("pamphletFlag" -> int.toString)
  }
}

object PamphletFlag {

  case object All extends PamphletFlag {
    val int = 0
  }
  case object OnlyAccept extends PamphletFlag {
    val int = 1
  }
}
