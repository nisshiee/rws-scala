package rwsscala.ichiba

import org.specs2._, matcher.DataTables
import scalaz._, Scalaz._

class MaxPriceSpec extends Specification with DataTables { def is =

  "MaxPrice"                                                                                        ^
    "apply/unapply"                                                                                 ^
      "0以上の場合、その値をvalueに保持するMaxPriceオブジェクトが返る"                              ! e1^
      "0未満の場合、0をvalueに保持するMaxPriceオブジェクトが返る"                                   ! e2^
      "MaxPrice.OffをunapplyするとNoneが返る"                                                       ! e3^
                                                                                                    p^
    "param"                                                                                         ^
      "MaxPrice.Offの場合Seq()が返る"                                                               ! e4^
      "MaxPrice.Onの場合Seq(\"maxPrice\" -> \"$value\")が返る"                                      ! e5^
                                                                                                    p^
    "long2maxPrice"                                                                                 ^
      "LongからMaxPriceにapplyでimplicit conversionされる"                                          ! e6^
                                                                                                    p^
    "int2maxPrice"                                                                                  ^
      "IntからMaxPriceにapplyでimplicit conversionされる"                                           ! e7^
                                                                                                    p^
    "longOpt2maxPrice"                                                                              ^
      "Option[Long]からMaxPriceにapplyでimplicit conversionされる"                                  ! e8^
                                                                                                    p^
    "intOpt2maxPrice"                                                                               ^
      "Option[Int]からMaxPriceにapplyでimplicit conversionされる"                                   ! e9^
                                                                                                    end

  def e1 =
    "input" | "output" |
    0L      ! 0L       |
    1L      ! 1L       |
    999999L ! 999999L  |> { (input, output) =>
      MaxPrice(input) match {
        case MaxPrice(actual) => actual must equalTo(output)
        case _ => ko
      }
    }

  def e2 =
    "input" | "output" |
    -1L     ! 0L       |
    -100L   ! 0L       |
    -99999L ! 0L       |> { (input, output) =>
      MaxPrice(input) match {
        case MaxPrice(actual) => actual must equalTo(output)
        case _ => ko
      }
    }

  def e3 = MaxPrice.Off match {
    case MaxPrice(_) => ko
    case _ => ok
  }

  def e4 = MaxPrice.Off.param must beEmpty


  def e5 =
    "input" | "output" |
    -1L     ! "0"      |
    -100L   ! "0"      |
    -99999L ! "0"      |
    0L      ! "0"      |
    1L      ! "1"      |
    999999L ! "999999" |> { (input, output) =>
      MaxPrice(input).param must equalTo(Seq("maxPrice" -> output))
    }

  def e6 =
    "long" | "maxPrice"    |
    -1L    ! MaxPrice(-1L) |
    0L     ! MaxPrice(0L)  |
    1L     ! MaxPrice(1L)  |
    31L    ! MaxPrice(31)  |> { (long, maxPrice) =>
      val conv: MaxPrice = long
      conv must equalTo(maxPrice)
    }

  def e7 =
    "int" | "maxPrice"    |
    -1    ! MaxPrice(-1L) |
    0     ! MaxPrice(0L)  |
    1     ! MaxPrice(1L)  |
    31    ! MaxPrice(31)  |> { (int, maxPrice) =>
      val conv: MaxPrice = int
      conv must equalTo(maxPrice)
    }

  def e8 =
    "longOpt"  | "maxPrice"    |
    None       ! MaxPrice.Off  |
    -1L.some   ! MaxPrice(-1L) |
    0L.some    ! MaxPrice(0L)  |
    1L.some    ! MaxPrice(1L)  |
    31L.some   ! MaxPrice(31)  |> { (longOpt: Option[Long], maxPrice) =>
      val conv: MaxPrice = longOpt
      conv must equalTo(maxPrice)
    }

  def e9 =
    "intOpt"  | "maxPrice"    |
    None      ! MaxPrice.Off  |
    -1.some   ! MaxPrice(-1L) |
    0.some    ! MaxPrice(0L)  |
    1.some    ! MaxPrice(1L)  |
    31.some   ! MaxPrice(31)  |> { (intOpt: Option[Int], maxPrice) =>
      val conv: MaxPrice = intOpt
      conv must equalTo(maxPrice)
    }
}
