package rwsscala.ichiba

import org.specs2._, matcher.DataTables

import rwsscala.util._

class SortSpec extends Specification with DataTables { def is =

  "Sort"                                                                                            ^
    "param"                                                                                         ^
      "Standardの場合Seq()が返る"                                                                   ! e1^
      "Standard以外の場合、Seq(\"sort\" -> str)が返る"                                              ! e2^
                                                                                                    p^
    "CaseCode"                                                                                      ^
      "Sort => String"                                                                              ! e3^
      "String => Some(Sort)"                                                                        ! e4^
      "String => None"                                                                              ! e5^
                                                                                                    end

  import Sort._
  import ItemSearchParameters._
  import ItemSearchCaseCodes._

  def e1 = {
    import ItemSearchParameters._
    (Standard: Sort).param must beEmpty
  }

  def e2 =
    "sort"              | "code"             |
    AffiliateRateAsc    ! "+affiliateRate"   |
    AffiliateRateDesc   ! "-affiliateRate"   |
    ReviewCountAsc      ! "+reviewCount"     |
    ReviewCountDesc     ! "-reviewCount"     |
    ReviewAverageAsc    ! "+reviewAverage"   |
    ReviewAverageDesc   ! "-reviewAverage"   |
    ItemPriceAsc        ! "+itemPrice"       |
    ItemPriceDesc       ! "-itemPrice"       |
    UpdateTimestampAsc  ! "+updateTimestamp" |
    UpdateTimestampDesc ! "-updateTimestamp" |> { (sort: Sort, code) =>
      import ItemSearchParameters._
      sort.param must equalTo(Seq("sort" -> code))
    }

  def e3 =
    "sort"              | "str"              |
    Standard            ! "standard"         |
    AffiliateRateAsc    ! "+affiliateRate"   |
    AffiliateRateDesc   ! "-affiliateRate"   |
    ReviewCountAsc      ! "+reviewCount"     |
    ReviewCountDesc     ! "-reviewCount"     |
    ReviewAverageAsc    ! "+reviewAverage"   |
    ReviewAverageDesc   ! "-reviewAverage"   |
    ItemPriceAsc        ! "+itemPrice"       |
    ItemPriceDesc       ! "-itemPrice"       |
    UpdateTimestampAsc  ! "+updateTimestamp" |
    UpdateTimestampDesc ! "-updateTimestamp" |> { (sort: Sort, str) =>
      strCode(sort) must equalTo(str)
    }

  def e4 =
    "str"              || "sort"              | 
    "standard"         !! Standard            | 
    "+affiliateRate"   !! AffiliateRateAsc    | 
    "-affiliateRate"   !! AffiliateRateDesc   | 
    "+reviewCount"     !! ReviewCountAsc      | 
    "-reviewCount"     !! ReviewCountDesc     | 
    "+reviewAverage"   !! ReviewAverageAsc    | 
    "-reviewAverage"   !! ReviewAverageDesc   | 
    "+itemPrice"       !! ItemPriceAsc        | 
    "-itemPrice"       !! ItemPriceDesc       | 
    "+updateTimestamp" !! UpdateTimestampAsc  | 
    "-updateTimestamp" !! UpdateTimestampDesc |> { (str, sort: Sort) =>
      fromCaseCode[String, Sort](str) must beSome.which(sort ==)
    }

  def e5 =
    "str"          || "none" | 
    ""             !! None   |
    (null: String) !! None   |
    "12345"        !! None   |
    "hoge"         !! None   |> { (str: String, _) =>
      fromCaseCode[String, Sort](str) must beNone
    }

}
