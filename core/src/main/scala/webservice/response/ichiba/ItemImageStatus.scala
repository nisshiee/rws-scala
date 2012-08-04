package jp.co.rakuten.webservice

import scalaz._, Scalaz._

sealed trait ItemImageStatus
case object NoItemImage extends ItemImageStatus
case class HasItemImage(smallUrls: List[String], mediumUrls: List[String]) extends ItemImageStatus

object ItemImageStatus {

  def parseVld(flag: Int, smallUrls: List[String], mediumUrls: List[String]) = flag match {
    case 0 => NoItemImage.success
    case 1 => HasItemImage(smallUrls, mediumUrls).success
    case _ => JsonParseError("imageFlag must be in 0, 1").failure
  }
}
