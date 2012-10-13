package rwsscala.param.ichiba

import rwsscala.util._

sealed trait Asuraku extends Parameter
case object AsurakuAll extends Asuraku {
  def param = Seq()
}
case class OnlyAsuraku(area: AsurakuArea) extends Asuraku {
  def param = area match {
    case AllArea => Seq("asurakuFlag" -> "1")
    case _ => Seq("asurakuFlag" -> "1", "asurakuArea" -> area.code.toString)
  }
}

trait Asurakus {

  implicit def area2asuraku(area: AsurakuArea): Asuraku = OnlyAsuraku(area)
}
