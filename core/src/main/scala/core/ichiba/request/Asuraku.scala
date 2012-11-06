package rwsscala.ichiba

sealed trait Asuraku

object Asuraku {

  case object All extends Asuraku
  case class OnlyAccept(area: AsurakuArea) extends Asuraku
}

trait Asurakus {

  implicit def area2asuraku(area: AsurakuArea): Asuraku = Asuraku.OnlyAccept(area)
}
