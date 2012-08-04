package jp.co.rakuten.webservice

case class Hit(value: Int) extends Paramater {
  def param = value match {
    case 30 => Seq()
    case v => Seq("hits" -> v.toString)
  }
}

trait Hits {
  implicit def int2hit(value: Int): Hit = Hit(value)
}
