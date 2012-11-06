package rwsscala.util

import scalaz._, Scalaz._

trait CaseCode[C, A] {
  def toCode: A => C
  def fromCode: C => Option[A]
}

trait CaseCodes {

  def caseCode[C, A](a: A)(implicit evidence: CaseCode[C, A]) = implicitly[CaseCode[C, A]].toCode(a)
  def fromCaseCode[C, A](c: C)(implicit evidence: CaseCode[C, A]) = implicitly[CaseCode[C, A]].fromCode(c)

  def intCode[C, A](a: A)(implicit evidence: CaseCode[C, A], conversion: C => Int): Int =
    implicitly[CaseCode[C, A]].toCode(a)
  def strCode[C, A](a: A)(implicit evidence: CaseCode[C, A], conversion: C => String): String =
    implicitly[CaseCode[C, A]].toCode(a)
}
