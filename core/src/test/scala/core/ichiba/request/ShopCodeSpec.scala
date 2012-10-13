package rwsscala.ichiba

import org.specs2._, matcher.DataTables
import scalaz._, Scalaz._

class ShopCodeSpec extends Specification with DataTables { def is =

  "ShopCode"                                                                                        ^
    "apply/unapply"                                                                                 ^
      "引数をvalueに保持するShopCodeオブジェクトが返る"                                             ! e1^
      "ShopCode.OffをunapplyするとNoneが返る"                                                       ! e2^
                                                                                                    p^
    "param"                                                                                         ^
      "ShopCode.Offの場合Seq()が返る"                                                               ! e3^
      "ShopCode.Onの場合Seq(\"shopCode\" -> \"$value\")が返る"                                      ! e4^
                                                                                                    p^
    "str2shopCode"                                                                                  ^
      "StringからShopCodeにapplyでimplicit conversionされる"                                        ! e5^
                                                                                                    p^
    "strOpt2shopCode"                                                                               ^
      "Option[String]からShopCodeにapplyでimplicit conversionされる"                                ! e6^
                                                                                                    end

  def e1 =
    "input"  || "output" |
    "test"   !! "test"   |
    "日本語" !! "日本語" |> { (input, output) =>
      ShopCode(input) match {
        case ShopCode(actual) => actual must equalTo(output)
        case _ => ko
      }
    }

  def e2 = ShopCode.Off match {
    case ShopCode(_) => ko
    case _ => ok
  }

  def e3 = ShopCode.Off.param must beEmpty


  def e4 =
    "input"  || "output" |
    "test"   !! "test"   |
    "日本語" !! "日本語" |> { (input, output) =>
      ShopCode(input).param must equalTo(Seq("shopCode" -> output))
    }

  def e5 =
    "str"    || "shopCode"         |
    "test"   !! ShopCode("test")   |
    "日本語" !! ShopCode("日本語") |> { (str, shopCode) =>
      val conv: ShopCode = str
      conv must equalTo(shopCode)
    }

  def e6 =
    "strOpt"      || "shopCode"         |
    None          !! ShopCode.Off       |
    "test".some   !! ShopCode("test")   |
    "日本語".some !! ShopCode("日本語") |> { (strOpt: Option[String] , shopCode) =>
      val conv: ShopCode = strOpt
      conv must equalTo(shopCode)
    }

}
