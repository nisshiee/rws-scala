package rwsscala.param

import rwsscala.Implicits._
import org.specs2._, matcher.DataTables

class PageSpec extends Specification with DataTables { def is =

  "Page"                                                                                            ^
    "apply/unapply"                                                                                 ^
      "1-100の場合、その値をvalueに保持するPageオブジェクトが返る"                                  ! e1^
      "1未満の場合、1をvalueに保持するPageオブジェクトが返る"                                       ! e2^
      "101以上の場合、100をvalueに保持するPageオブジェクトが返る"                                   ! e3^
                                                                                                    p^
    "param"                                                                                         ^
      "1の場合Seq()が返る"                                                                          ! e4^
      "v != 1の場合Seq(\"page\" -> \"$v\")が返る"                                                   ! e5^
                                                                                                    p^
    "int2page"                                                                                      ^
      "IntからPageにapplyでimplicit conversionされる"                                               ! e6^
                                                                                                    end

  def e1 =
    "input" | "output" |
    1       ! 1        |
    15      ! 15       |
    100     ! 100      |> { (input, output) =>
      (Page(input).value must equalTo(output)) and
      (Page(input) match {
        case Page(v) => v must equalTo(output)
        case _ => ko
      })
    }

  def e2 =
    "input" | "output" |
    -99     ! 1        |
    -1      ! 1        |
    0       ! 1        |> { (input, output) =>
      (Page(input).value must equalTo(output)) and
      (Page(input) match {
        case Page(v) => v must equalTo(output)
        case _ => ko
      })
    }

  def e3 =
    "input" | "output" |
    101     ! 100      |
    9999    ! 100      |> { (input, output) =>
      (Page(input).value must equalTo(output)) and
      (Page(input) match {
        case Page(v) => v must equalTo(output)
        case _ => ko
      })
    }

  def e4 =
    "value" | "result" |
    1       ! Seq()    |
    -3      ! Seq()    |> { (value, result) =>
      Page(value).param must beEmpty
    }

  def e5 =
    "value" | "result"             |
    2       ! Seq("page" -> "2")   |
    45      ! Seq("page" -> "45")  |
    100     ! Seq("page" -> "100") |
    101     ! Seq("page" -> "100") |> { (value, result) =>
      Page(value).param must equalTo(result)
    }

  def e6 =
    "int" | "page"    |
    -1    ! Page(-1)  |
    1     ! Page(1)   |
    45    ! Page(45)  |
    100   ! Page(100) |
    101   ! Page(101) |> { (int, page) =>
      val conv: Page = int
      conv must equalTo(page)
    }
}
