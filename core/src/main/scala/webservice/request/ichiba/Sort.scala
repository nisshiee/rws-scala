package jp.co.rakuten.webservice

sealed trait Sort extends Parameter {
  def str: String
  def param = this match {
    case StandardSort => Seq()
    case s => Seq("sort" -> s.str)
  }
}
case object AffiliateRateAsc extends Sort {
  val str = "+affiliateRate"
}
case object AffiliateRateDesc extends Sort {
  val str = "-affiliateRate"
}
case object ReviewCountAsc extends Sort {
  val str = "+reviewCount"
}
case object ReviewCountDesc extends Sort {
  val str = "-reviewCount"
}
case object ReviewAverageAsc extends Sort {
  val str = "+reviewAverage"
}
case object ReviewAverageDesc extends Sort {
  val str = "-reviewAverage"
}
case object ItemPriceAsc extends Sort {
  val str = "+itemPrice"
}
case object ItemPriceDesc extends Sort {
  val str = "-itemPrice"
}
case object UpdateTimestampAsc extends Sort {
  val str = "+updateTimestamp"
}
case object UpdateTimestampDesc extends Sort {
  val str = "-updateTimestamp"
}
case object StandardSort extends Sort {
  val str = "standard"
}

