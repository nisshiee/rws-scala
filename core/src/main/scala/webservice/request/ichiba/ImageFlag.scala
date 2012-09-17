package jp.co.rakuten.webservice

sealed trait ImageFlag extends Parameter {
  def int: Int
  def param = this match {
    case ImageAll => Seq()
    case _ => Seq("imageFlag" -> int.toString)
  }
}
case object ImageAll extends ImageFlag {
  val int = 0
}
case object HasImage extends ImageFlag {
  val int = 1
}
