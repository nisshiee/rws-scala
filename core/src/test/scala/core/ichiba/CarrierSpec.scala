package rwsscala.ichiba

import rwsscala._
import org.specs2._, matcher.DataTables
import scalaz._, Scalaz._

import rwsscala.util._

class CarrierSpec extends Specification with DataTables { def is =

  "Carrier"                                                                                         ^
    "param"                                                                                         ^
      "PCの場合Seq()が返る"                                                                         ! e1^
      "Mobileの場合Seq(\"carrier\" -> \"1\")が返る"                                                 ! e2^
      "SPの場合Seq(\"carrier\" -> \"2\")が返る"                                                     ! e3^
                                                                                                    p^
    "CaseCode"                                                                                       ^
      "Carrier => Int"                                                                              ! e6^
      "Int => Some(Carrier)"                                                                        ! e7^
      "Int => None"                                                                                 ! e8^
                                                                                                    end

  import Carrier._
  import ItemSearchParameters._
  import ItemSearchCaseCodes._

  def e1 = (PC: Carrier).param must beEmpty
  def e2 = (Mobile: Carrier).param must equalTo(Seq("carrier" -> "1"))
  def e3 = (SP: Carrier).param must equalTo(Seq("carrier" -> "2"))

  def e6 =
    "carrier" | "int" |
    PC        ! 0     |
    Mobile    ! 1     |
    SP        ! 2     |> { (c: Carrier, i: Int) =>
      intCode(c) must equalTo(i)
    }

  def e7 =
    "int" | "carrier" |
    0     ! PC        |
    1     ! Mobile    |
    2     ! SP        |> { (i: Int, c: Carrier) =>
      fromCaseCode[Int, Carrier](i) must beSome.which(c ==)
    }

  def e8 =
    "int" | "none" |
    -99   ! None   |
    -1    ! None   |
    3     ! None   |
    99    ! None   |> { (i: Int, n: Option[Carrier]) =>
      fromCaseCode[Int, Carrier](i) must beNone
    }
}
