package jp.co.rakuten.webservice

import util._

sealed trait PamphletFlag extends Parameter {
  def int: Int
  def param = this match {
    case PamphletAll => Seq()
    case _ => Seq("pamphletFlag" -> int.toString)
  }
}
case object PamphletAll extends PamphletFlag {
  val int = 0
}
case object OnlyAllowPamphlet extends PamphletFlag {
  val int = 1
}
