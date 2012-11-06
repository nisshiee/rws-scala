package rwsscala.ichiba

import org.specs2._, matcher.DataTables

import rwsscala.util._

class AvailabilitySpec extends Specification with DataTables { def is =

  "Availability"                                                                                    ^
    "param"                                                                                         ^
      "OnlyAvailableの場合Seq()が返る"                                                              ! e1^
      "Allの場合Seq(\"availability\" -> \"0\")が返る"                                               ! e2^
                                                                                                    p^
    "CaseCode"                                                                                       ^
      "Availability => Int"                                                                         ! e3^
      "Int => Some(Availability)"                                                                   ! e4^
      "Int => None"                                                                                 ! e5^
                                                                                                    end

  import Availability._

  def e1 = {
    import ItemSearchParameters._
    (OnlyAvailable: Availability).param must beEmpty
  }
  def e2 = {
    import ItemSearchParameters._
    (All: Availability).param must equalTo(Seq("availability" -> "0"))
  }

  def e3 =
    "availability" | "int" |
    OnlyAvailable  ! 1     |
    All            ! 0     |> { (a: Availability, i: Int) =>
      import ItemSearchCaseCodes._
      intCode(a) must equalTo(i)
    }

  def e4 =
    "int" | "availability" |
    1     ! OnlyAvailable  |
    0     ! All            |> { (i: Int, a: Availability) =>
      import ItemSearchCaseCodes._
      fromCaseCode[Int, Availability](i) must beSome.which(a ==)
    }

  def e5 =
    "int" | "none" |
    -99   ! None   |
    -1    ! None   |
    2     ! None   |
    99    ! None   |> { (i: Int, n: Option[Availability]) =>
      import ItemSearchCaseCodes._
      fromCaseCode[Int, Availability](i) must beNone
    }

}
