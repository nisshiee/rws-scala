package jp.co.rakuten.webservice

sealed trait OrFlag extends Paramater {
  def int: Int
  def param = Seq("orFlag" -> int.toString)
}
case object AndSearch extends OrFlag {
  val int = 0
}
case object OrSearch extends OrFlag {
  val int = 1
}
