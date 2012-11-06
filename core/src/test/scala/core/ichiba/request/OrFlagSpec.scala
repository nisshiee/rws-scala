package rwsscala.ichiba

import org.specs2._, matcher.DataTables

import rwsscala.util._

class OrFlagSpec extends Specification with DataTables { def is =

  "OrFlag"                                                                                          ^
    "param"                                                                                         ^
      "Andの場合Seq()が返る"                                                                        ! e1^
      "Orの場合Seq(\"orFlag\" -> \"1\")が返る"                                                      ! e2^
                                                                                                    p^
    "CaseCode"                                                                                       ^
      "ImageFlag => Int"                                                                            ! e3^
      "Int => Some(ImageFlag)"                                                                      ! e4^
      "Int => None"                                                                                 ! e5^
                                                                                                    end

  import OrFlag._
  import ItemSearchParameters._
  import ItemSearchCaseCodes._

  def e1 = (And: OrFlag).param must beEmpty
  def e2 = (Or: OrFlag).param must equalTo(Seq("orFlag" -> "1"))

  def e3 =
    "orFlag" | "int" |
    And      ! 0     |
    Or       ! 1     |> { (o: OrFlag, i: Int) =>
      intCode(o) must equalTo(i)
    }

  def e4 =
    "int" | "orFlag" |
    0     ! And      |
    1     ! Or       |> { (i: Int, o: OrFlag) =>
      fromCaseCode[Int, OrFlag](i) must beSome.which(o ==)
    }

  def e5 =
    "int" | "none" |
    -99   ! None   |
    -1    ! None   |
    2     ! None   |
    99    ! None   |> { (i: Int, n: Option[OrFlag]) =>
      fromCaseCode[Int, OrFlag](i) must beNone
    }
}
