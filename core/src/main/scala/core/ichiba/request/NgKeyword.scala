package rwsscala.ichiba

import scalaz._, Scalaz._

sealed trait NgKeyword

object NgKeyword {

  private case class On(value: String) extends NgKeyword
  case object Off extends NgKeyword

  def apply(value: String): NgKeyword = On(value)

  def unapply(n: NgKeyword): Option[String] = n match {
    case On(v) => v.some
    case Off => none
  }
}

trait NgKeywords {

  implicit def str2ngKeyword(value: String): NgKeyword = NgKeyword(value)
  implicit def strOpt2ngKeyword(opt: Option[String]): NgKeyword =
    opt ∘ str2ngKeyword | NgKeyword.Off
}

