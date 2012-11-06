package rwsscala.ichiba

import org.specs2._, matcher.DataTables
import scalaz._, Scalaz._

import rwsscala.util._

class MinPriceSpec extends Specification with DataTables { def is =

  "MinPrice"                                                                                        ^
    "apply/unapply"                                                                                 ^
      "0以上の場合、その値をvalueに保持するMinPriceオブジェクトが返る"                              ! e1^
      "0未満の場合、0をvalueに保持するMinPriceオブジェクトが返る"                                   ! e2^
      "MinPrice.OffをunapplyするとNoneが返る"                                                       ! e3^
                                                                                                    p^
    "param"                                                                                         ^
      "MinPrice.Offの場合Seq()が返る"                                                               ! e4^
      "MinPrice.Onの場合Seq(\"minPrice\" -> \"$value\")が返る"                                      ! e5^
                                                                                                    p^
    "long2minPrice"                                                                                 ^
      "LongからMinPriceにapplyでimplicit conversionされる"                                          ! e6^
                                                                                                    p^
    "int2minPrice"                                                                                  ^
      "IntからMinPriceにapplyでimplicit conversionされる"                                           ! e7^
                                                                                                    p^
    "longOpt2minPrice"                                                                              ^
      "Option[Long]からMinPriceにapplyでimplicit conversionされる"                                  ! e8^
                                                                                                    p^
    "intOpt2minPrice"                                                                               ^
      "Option[Int]からMinPriceにapplyでimplicit conversionされる"                                   ! e9^
                                                                                                    end

  def e1 =
    "input" | "output" |
    0L      ! 0L       |
    1L      ! 1L       |
    999999L ! 999999L  |> { (input, output) =>
      MinPrice(input) match {
        case MinPrice(actual) => actual must equalTo(output)
        case _ => ko
      }
    }

  def e2 =
    "input" | "output" |
    -1L     ! 0L       |
    -100L   ! 0L       |
    -99999L ! 0L       |> { (input, output) =>
      MinPrice(input) match {
        case MinPrice(actual) => actual must equalTo(output)
        case _ => ko
      }
    }

  def e3 = MinPrice.Off match {
    case MinPrice(_) => ko
    case _ => ok
  }

  def e4 = {
    import ItemSearchParameters._
    (MinPrice.Off: MinPrice).param must beEmpty
  }


  def e5 =
    "input" | "output" |
    -1L     ! "0"      |
    -100L   ! "0"      |
    -99999L ! "0"      |
    0L      ! "0"      |
    1L      ! "1"      |
    999999L ! "999999" |> { (input, output) =>
      import ItemSearchParameters._
      MinPrice(input).param must equalTo(Seq("minPrice" -> output))
    }

  def e6 =
    "long" | "minPrice"    |
    -1L    ! MinPrice(-1L) |
    0L     ! MinPrice(0L)  |
    1L     ! MinPrice(1L)  |
    31L    ! MinPrice(31)  |> { (long, minPrice) =>
      val conv: MinPrice = long
      conv must equalTo(minPrice)
    }

  def e7 =
    "int" | "minPrice"    |
    -1    ! MinPrice(-1L) |
    0     ! MinPrice(0L)  |
    1     ! MinPrice(1L)  |
    31    ! MinPrice(31)  |> { (int, minPrice) =>
      val conv: MinPrice = int
      conv must equalTo(minPrice)
    }

  def e8 =
    "longOpt"  | "minPrice"    |
    None       ! MinPrice.Off  |
    -1L.some   ! MinPrice(-1L) |
    0L.some    ! MinPrice(0L)  |
    1L.some    ! MinPrice(1L)  |
    31L.some   ! MinPrice(31)  |> { (longOpt: Option[Long], minPrice) =>
      val conv: MinPrice = longOpt
      conv must equalTo(minPrice)
    }

  def e9 =
    "intOpt"  | "minPrice"    |
    None      ! MinPrice.Off  |
    -1.some   ! MinPrice(-1L) |
    0.some    ! MinPrice(0L)  |
    1.some    ! MinPrice(1L)  |
    31.some   ! MinPrice(31)  |> { (intOpt: Option[Int], minPrice) =>
      val conv: MinPrice = intOpt
      conv must equalTo(minPrice)
    }
}
