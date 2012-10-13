package rwsscala.param.ichiba

import org.specs2._, matcher.DataTables

class SortSpec extends Specification with DataTables { def is =

  "Sort"                                                                                            ^
    "param"                                                                                         ^
      "Standardの場合Seq()が返る"                                                                   ! e1^
      "Standard以外の場合、Seq(\"sort\" -> str)が返る"                                              ! e2^
                                                                                                    end

  def e1 = Sort.Standard.param must beEmpty
  def e2 =
    "sort"                   | "code"             |
    Sort.AffiliateRateAsc    ! "+affiliateRate"   |
    Sort.AffiliateRateDesc   ! "-affiliateRate"   |
    Sort.ReviewCountAsc      ! "+reviewCount"     |
    Sort.ReviewCountDesc     ! "-reviewCount"     |
    Sort.ReviewAverageAsc    ! "+reviewAverage"   |
    Sort.ReviewAverageDesc   ! "-reviewAverage"   |
    Sort.ItemPriceAsc        ! "+itemPrice"       |
    Sort.ItemPriceDesc       ! "-itemPrice"       |
    Sort.UpdateTimestampAsc  ! "+updateTimestamp" |
    Sort.UpdateTimestampDesc ! "-updateTimestamp" |> { (sort, code) =>
      sort.param must equalTo(Seq("sort" -> code))
    }

}
