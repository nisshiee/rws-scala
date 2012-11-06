package rwsscala

import scalaz._, Scalaz._

sealed trait Page {

  def value: Int
}

object Page {

  private case class Impl(value: Int) extends Page {

    def param = value match {
      case 1 => Seq()
      case v => Seq("page" -> v.toString)
    }
  }

  def apply(value: Int): Page = value match {
    case i if i < 1 => Impl(1)
    case i if i > 100 => Impl(100)
    case _ => Impl(value)
  }

  def unapply(p: Page): Option[Int] = p match {
    case Impl(v) => v.some
  }
}

trait Pages {
  implicit def int2page(value: Int): Page = Page(value)
}
