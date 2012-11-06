package rwsscala.ichiba

import rwsscala._
import rwsscala.util._

import ItemSearchCaseCodes._

trait ItemSearchParameters {

  implicit object ItemSearchBaseParameter extends Parameter[ItemSearchBase] {
    def param: ItemSearchBase => Seq[(String, String)] = {
      case ItemSearchBase(keyword, genreId, itemCode) => Seq(
         keyword map { "keyword" -> _ }
        ,genreId map { "genreId" -> _.toString }
        ,itemCode map { "itemCode" -> _ }
      ) flatten
    }
  }

  implicit object ShopCodeParameter extends Parameter[ShopCode] {
    def param: ShopCode => Seq[(String, String)] = {
      case ShopCode(s) => Seq("shopCode" -> s)
      case _ => Seq()
    }
  }

  implicit object HitParameter extends Parameter[Hit] {
    def param: Hit => Seq[(String, String)] = {
      case Hit(30) => Seq()
      case Hit(i) => Seq("hits" -> i.toString)
    }
  }

  implicit object PageParameter extends Parameter[Page] {
    def param: Page => Seq[(String, String)] = {
      case Page(1) => Seq()
      case Page(i) => Seq("page" -> i.toString)
    }
  }

  implicit object SortParameter extends Parameter[Sort] {
    def param: Sort => Seq[(String, String)] = {
      case Sort.Standard => Seq()
      case s => Seq("sort" -> strCode(s)) }
  }

  implicit object MinPriceParameter extends Parameter[MinPrice] {
    def param: MinPrice => Seq[(String, String)] = {
      case MinPrice(i) => Seq("minPrice" -> i.toString)
      case _ => Seq()
    }
  }

  implicit object MaxPriceParameter extends Parameter[MaxPrice] {
    def param: MaxPrice => Seq[(String, String)] = {
      case MaxPrice(i) => Seq("maxPrice" -> i.toString)
      case _ => Seq()
    }
  }

  implicit object AvailabilityParameter extends Parameter[Availability] {
    import Availability._
    def param: Availability => Seq[(String, String)] = {
      case OnlyAvailable => Seq()
      case a => Seq("availability" -> intCode(a).toString)
    }
  }

  implicit object SearchFieldParameter extends Parameter[SearchField] {
    import SearchField._
    def param: SearchField => Seq[(String, String)] = {
      case Strict => Seq()
      case s => Seq("field" -> intCode(s).toString)
    }
  }

  implicit object CarrierParameter extends Parameter[Carrier] {
    import Carrier._
    def param: Carrier => Seq[(String, String)] = {
      case PC => Seq()
      case c => Seq("carrier" -> intCode(c).toString)
    }
  }

  implicit object ImageFlagParameter extends Parameter[ImageFlag] {
    import ImageFlag._
    def param: ImageFlag => Seq[(String, String)] = {
      case All => Seq()
      case f => Seq("imageFlag" -> intCode(f).toString)
    }
  }

  implicit object OrFlagParameter extends Parameter[OrFlag] {
    import OrFlag._
    def param: OrFlag => Seq[(String, String)] = {
      case And => Seq()
      case o => Seq("orFlag" -> intCode(o).toString)
    }
  }

  implicit object NgKeywordParameter extends Parameter[NgKeyword] {
    import NgKeyword._
    def param: NgKeyword => Seq[(String, String)] = {
      case NgKeyword(s) => Seq("NGKeyword" -> s)
      case _ => Seq()
    }
  }

  implicit object PurchaseTypeParameter extends Parameter[PurchaseType] {
    import PurchaseType._
    def param: PurchaseType => Seq[(String, String)] = {
      case NormalOrder => Seq()
      case p => Seq("purchaseType" -> intCode(p).toString)
    }
  }

  implicit object ShipOverseaParameter extends Parameter[ShipOversea] {
    import ShipOversea._
    implicit val AreaCaseCode = OverseaAreaCaseCodes.ParameterCaseCode
    def param: ShipOversea => Seq[(String, String)] = {
      case All => Seq()
      case OnlyAccept(OverseaArea.AllCountry) => Seq("shipOverseasFlag" -> "1")
      case OnlyAccept(a) => Seq(
         "shipOverseasFlag" -> "1"
        ,"shipOverseasArea" -> strCode(a)
      )
    }
  }

  implicit object AsurakuParameter extends Parameter[Asuraku] {
    import Asuraku._
    def param: Asuraku => Seq[(String, String)] = {
      case All => Seq()
      case OnlyAccept(AsurakuArea.AllArea) => Seq("asurakuFlag" -> "1")
      case OnlyAccept(a) => Seq(
         "asurakuFlag" -> "1"
        ,"asurakuArea" -> intCode[Int, AsurakuArea](a).toString
      )
    }
  }

  implicit object PointRateParameter extends Parameter[PointRate] {
    import PointRate._
    def param: PointRate => Seq[(String, String)] = {
      case Off => Seq()
      case AnyRate => Seq("pointRateFlag" -> "1")
      case On(r) => Seq("pointRateFlag" -> "1", "pointRate" -> r.toString)
    }
  }

  implicit object PostageFlagParameter extends Parameter[PostageFlag] {
    import PostageFlag._
    def param: PostageFlag => Seq[(String, String)] = {
      case All => Seq()
      case p => Seq("postageFlag" -> intCode(p).toString)
    }
  }

  implicit object CreditCardFlagParameter extends Parameter[CreditCardFlag] {
    import CreditCardFlag._
    def param: CreditCardFlag => Seq[(String, String)] = {
      case All => Seq()
      case c => Seq("creditCardFlag" -> intCode(c).toString)
    }
  }

  implicit object GiftFlagParameter extends Parameter[GiftFlag] {
    import GiftFlag._
    def param: GiftFlag => Seq[(String, String)] = {
      case All => Seq()
      case g => Seq("giftFlag" -> intCode(g).toString)
    }
  }

  implicit object HasReviewFlagParameter extends Parameter[HasReviewFlag] {
    import HasReviewFlag._
    def param: HasReviewFlag => Seq[(String, String)] = {
      case All => Seq()
      case h => Seq("hasReviewFlag" -> intCode(h).toString)
    }
  }

  implicit object HasMovieFlagParameter extends Parameter[HasMovieFlag] {
    import HasMovieFlag._
    def param: HasMovieFlag => Seq[(String, String)] = {
      case All => Seq()
      case h => Seq("hasMovieFlag" -> intCode(h).toString)
    }
  }

  implicit object PamphletFlagParameter extends Parameter[PamphletFlag] {
    import PamphletFlag._
    def param: PamphletFlag => Seq[(String, String)] = {
      case All => Seq()
      case p => Seq("pamphletFlag" -> intCode(p).toString)
    }
  }

  implicit object AppointDeliveryDateFlagParameter extends Parameter[AppointDeliveryDateFlag] {
    import AppointDeliveryDateFlag._
    def param: AppointDeliveryDateFlag => Seq[(String, String)] = {
      case All => Seq()
      case a => Seq("appointDeliveryDateFlag" -> intCode(a).toString)
    }
  }
}

object ItemSearchParameters extends ItemSearchParameters
