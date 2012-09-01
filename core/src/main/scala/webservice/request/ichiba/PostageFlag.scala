package jp.co.rakuten.webservice

sealed trait PostageFlag extends Parameter {
  def int: Int
  def param = Seq("postageFlag" -> int.toString)
}
case object PostageAll extends PostageFlag {
  val int = 0
}
case object OnlyFreePostage extends PostageFlag {
  val int = 1
}
