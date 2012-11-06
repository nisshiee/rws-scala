package rwsscala.ichiba

sealed trait Sort

object Sort {

  case object AffiliateRateAsc extends Sort
  case object AffiliateRateDesc extends Sort
  case object ReviewCountAsc extends Sort
  case object ReviewCountDesc extends Sort
  case object ReviewAverageAsc extends Sort
  case object ReviewAverageDesc extends Sort
  case object ItemPriceAsc extends Sort
  case object ItemPriceDesc extends Sort
  case object UpdateTimestampAsc extends Sort
  case object UpdateTimestampDesc extends Sort
  case object Standard extends Sort

}
