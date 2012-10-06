package jp.co.rakuten.webservice

import org.specs2._, matcher.DataTables
import scalaz._, Scalaz._

class ItemImageStatusSpec extends Specification with DataTables { def is =

  "ItemImageStatus"                                                                                 ^
    "parseVld"                                                                                      ^
      "flagが0の場合、NoItemImageがsuccessで返る"                                                   ! e1^
      "flagが1の場合、smallUrlsとmediumUrlを持つHasItemImageがsuccessで返る"                        ! e2^
      "flagが0,1以外の場合、JsonParseErrorをfailureで返す"                                          ! e3^
                                                                                                    end

  def e1 =
    "smallUrls" | "mediumUrls" |
    List()      ! List()       |
    List("url") ! List()       |
    List()      ! List("url")  |
    List("url") ! List("url")  |> { (smallUrls, mediumUrls) =>
      ItemImageStatus.parseVld(0, smallUrls, mediumUrls) must equalTo(NoItemImage.success)
    }

  def e2 =
    "smallUrls" | "mediumUrls" |
    List()      ! List()       |
    List("url") ! List()       |
    List()      ! List("url")  |
    List("url") ! List("url")  |> { (smallUrls, mediumUrls) =>
      ItemImageStatus.parseVld(1, smallUrls, mediumUrls).toEither must beRight.like {
        case HasItemImage(s, m) => (s, m) must equalTo(smallUrls -> mediumUrls)
      }
    }

  def e3 =
    "flag" | "smallUrls" | "mediumUrls" |
    -1     ! List()      ! List()       |
    2      ! List("url") ! List()       |
    -50    ! List()      ! List("url")  |
    99     ! List("url") ! List("url")  |> { (flag, smallUrls, mediumUrls) =>
      ItemImageStatus.parseVld(flag, smallUrls, mediumUrls).toEither must beLeft.like {
        case JsonParseError(msg) => msg must contain("imageFlag")
      }
    }

}
