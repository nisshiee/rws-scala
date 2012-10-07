package jp.co.rakuten.webservice

import param._, ichiba._

object Implicits
  extends ApplicationIds
  with AffiliateIds
  with ShopCodes
  with Hits
  with Pages
  with PointRates
  with MinPrices
  with MaxPrices
  with NgKeywords
  with MaxAffiliateRates
  with MinAffiliateRates
  with Asurakus
  with ShipOverseas
  with ApiErrors
{
}
