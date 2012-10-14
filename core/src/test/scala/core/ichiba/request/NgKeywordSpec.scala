package rwsscala.ichiba

import org.specs2._, matcher.DataTables
import scalaz._, Scalaz._

class NgKeywordSpec extends Specification with DataTables { def is =

  "NgKeyword"                                                                                       ^
    "apply/unapply"                                                                                 ^
      "文字列に適用すると、その値をvalueに保持するNgKeywordオブジェクトが返る"                      ! e1^
      "NgKeyword.OffをunapplyするとNoneが返る"                                                      ! e2^
                                                                                                    p^
    "param"                                                                                         ^
      "NgKeyword.Offの場合Seq()が返る"                                                              ! e3^
      "NgKeyword.Onの場合Seq(\"NGKeyword\" -> \"$value\")が返る"                                    ! e4^
                                                                                                    p^
    "str2ngKeyword"                                                                                 ^
      "StringからNgKeywordにapplyでimplicit conversionされる"                                       ! e5^
                                                                                                    p^
    "strOpt2ngKeyword"                                                                              ^
      "Option[String]からNgKeywordにapplyでimplicit conversionされる"                               ! e6^
                                                                                                    end

  def e1 =
    "input"  || "output" |
    "test"   !! "test"   |
    "日本語" !! "日本語" |> { (input, output) =>
      NgKeyword(input) match {
        case NgKeyword(actual) => actual must equalTo(output)
        case _ => ko
      }
    }

  def e2 = NgKeyword.Off match {
    case NgKeyword(_) => ko
    case _ => ok
  }

  def e3 = NgKeyword.Off.param must beEmpty

  def e4 =
    "input"  || "output" |
    "test"   !! "test"   |
    "日本語" !! "日本語" |> { (input, output) =>
      NgKeyword(input).param must equalTo(Seq("NGKeyword" -> output))
    }

  def e5 =
    "str"    || "ngKeyword"         |
    "test"   !! NgKeyword("test")   |
    "日本語" !! NgKeyword("日本語") |> { (str, ngKeyword) =>
      val conv: NgKeyword = str
      conv must equalTo(ngKeyword)
    }

  def e6 =
    "strOpt"      | "ngKeyword"         |
    None          ! NgKeyword.Off       |
    "test".some   ! NgKeyword("test")   |
    "日本語".some ! NgKeyword("日本語") |> { (strOpt, ngKeyword) =>
      val conv: NgKeyword = strOpt
      conv must equalTo(ngKeyword)
    }

}
