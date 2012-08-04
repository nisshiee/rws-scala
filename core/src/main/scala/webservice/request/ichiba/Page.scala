package jp.co.rakuten.webservice

case class Page(value: Int) extends Paramater {
  def param = value match {
    case 1 => Seq()
    case v => Seq("page" -> v.toString)
  }
}

trait Pages {
  implicit def int2page(value: Int): Page = Page(value)
}
