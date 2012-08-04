package jp.co.rakuten.webservice

sealed trait ImageFlag extends Paramater {
  def int: Int
  def param = Seq("imageFlag" -> int.toString)
}
case object ImageAll extends ImageFlag {
  val int = 0
}
case object ImageMust extends ImageFlag {
  val int = 1
}
