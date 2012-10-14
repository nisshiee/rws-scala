package rwsscala.ichiba

import rwsscala.util._

sealed trait Asuraku extends Parameter

object Asuraku {

  case object All extends Asuraku {
    def param = Seq()
  }
  case class OnlyAccept(area: AsurakuArea) extends Asuraku {
    def param = area match {
      case AsurakuArea.AllArea => Seq("asurakuFlag" -> "1")
      case _ => Seq("asurakuFlag" -> "1", "asurakuArea" -> area.code.toString)
    }
  }
}

trait Asurakus {

  implicit def area2asuraku(area: AsurakuArea): Asuraku = Asuraku.OnlyAccept(area)
}
