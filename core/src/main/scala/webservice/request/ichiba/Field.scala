package jp.co.rakuten.webservice

sealed trait Field extends Paramater {
  def int: Int
  def param = this match {
    case StrictSearch => Seq()
    case f => Seq("field" -> f.int.toString)
  }
}
case object BroadSearch extends Field {
  val int = 0
}
case object StrictSearch extends Field {
  val int = 1
}
