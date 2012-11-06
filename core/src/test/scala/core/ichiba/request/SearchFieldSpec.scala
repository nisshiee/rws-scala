package rwsscala.ichiba

import org.specs2._, matcher.DataTables

import rwsscala.util._

class SearchFieldSpec extends Specification with DataTables { def is =

  "SearchField"                                                                                     ^
    "param"                                                                                         ^
      "Strictの場合Seq()が返る"                                                                     ! e1^
      "Broadの場合Seq(\"field\" -> \"0\")が返る"                                                    ! e2^
                                                                                                    p^
    "CaseCode"                                                                                       ^
      "SearchField => Int"                                                                          ! e3^
      "Int => Some(SearchField)"                                                                    ! e4^
      "Int => None"                                                                                 ! e5^
                                                                                                    end

  import SearchField._
  import ItemSearchParameters._
  import ItemSearchCaseCodes._

  def e1 = (Strict: SearchField).param must beEmpty
  def e2 = (Broad: SearchField).param must equalTo(Seq("field" -> "0"))

  def e3 =
    "searchField" | "int" |
    Strict        ! 1     |
    Broad         ! 0     |> { (s: SearchField, i: Int) =>
      intCode(s) must equalTo(i)
    }

  def e4 =
    "int" | "searchField" |
    1     ! Strict        |
    0     ! Broad         |> { (i: Int, s: SearchField) =>
      fromCaseCode[Int, SearchField](i) must beSome.which(s ==)
    }

  def e5 =
    "int" | "none" |
    -99   ! None   |
    -1    ! None   |
    2     ! None   |
    99    ! None   |> { (i: Int, n: Option[SearchField]) =>
      fromCaseCode[Int, SearchField](i) must beNone
    }
}
