package jp.co.rakuten.webservice

sealed trait PamphletFlag extends Parameter {
  def int: Int
  def param = Seq("pamphletFlag" -> int.toString)
}
case object PamphletAll extends PamphletFlag {
  val int = 0
}
case object OnlyAllowPamphlet extends PamphletFlag {
  val int = 1
}
