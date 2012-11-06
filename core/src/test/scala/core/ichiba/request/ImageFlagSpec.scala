package rwsscala.ichiba

import org.specs2._, matcher.DataTables
import rwsscala.util._

class ImageFlagSpec extends Specification with DataTables { def is =

  "ImageFlag"                                                                                       ^
    "param"                                                                                         ^
      "Allの場合Seq()が返る"                                                                        ! e1^
      "OnlyHaveの場合Seq(\"imageFlag\" -> \"1\")が返る"                                             ! e2^
                                                                                                    p^
    "CaseCode"                                                                                       ^
      "ImageFlag => Int"                                                                            ! e3^
      "Int => Some(ImageFlag)"                                                                      ! e4^
      "Int => None"                                                                                 ! e5^
                                                                                                    end

  import ImageFlag._
  import ItemSearchParameters._
  import ItemSearchCaseCodes._

  def e1 = (All: ImageFlag).param must beEmpty
  def e2 = (OnlyHave: ImageFlag).param must equalTo(Seq("imageFlag" -> "1"))

  def e3 =
    "imageFlag" | "int" |
    All         ! 0     |
    OnlyHave    ! 1     |> { (f: ImageFlag, i: Int) =>
      intCode(f) must equalTo(i)
    }

  def e4 =
    "int" | "imageFlag" |
    0     ! All         |
    1     ! OnlyHave    |> { (i: Int, f: ImageFlag) =>
      fromCaseCode[Int, ImageFlag](i) must beSome.which(f ==)
    }

  def e5 =
    "int" | "none" |
    -99   ! None   |
    -1    ! None   |
    2     ! None   |
    99    ! None   |> { (i: Int, n: Option[ImageFlag]) =>
      fromCaseCode[Int, ImageFlag](i) must beNone
    }
}
