package rwsscala.ichiba

import rwsscala._
import rwsscala.util._

import scalaz._, Scalaz._

trait ItemSearchCaseCodes extends AsurakuAreaCaseCodes {

  implicit object SortCaseCode extends CaseCode[String, Sort] {
    import Sort._
    def toCode = {
      case AffiliateRateAsc => "+affiliateRate"
      case AffiliateRateDesc => "-affiliateRate"
      case ReviewCountAsc => "+reviewCount"
      case ReviewCountDesc => "-reviewCount"
      case ReviewAverageAsc => "+reviewAverage"
      case ReviewAverageDesc => "-reviewAverage"
      case ItemPriceAsc => "+itemPrice"
      case ItemPriceDesc => "-itemPrice"
      case UpdateTimestampAsc => "+updateTimestamp"
      case UpdateTimestampDesc => "-updateTimestamp"
      case Standard => "standard"
    }
    def fromCode = {
      case "+affiliateRate" => AffiliateRateAsc.some
      case "-affiliateRate" => AffiliateRateDesc.some
      case "+reviewCount" => ReviewCountAsc.some
      case "-reviewCount" => ReviewCountDesc.some
      case "+reviewAverage" => ReviewAverageAsc.some
      case "-reviewAverage" => ReviewAverageDesc.some
      case "+itemPrice" => ItemPriceAsc.some
      case "-itemPrice" => ItemPriceDesc.some
      case "+updateTimestamp" => UpdateTimestampAsc.some
      case "-updateTimestamp" => UpdateTimestampDesc.some
      case "standard" => Standard.some
      case _ => none
    }
  }

  implicit object AvailabilityCaseCode extends CaseCode[Int, Availability] {
    import Availability._
    def toCode = {
      case OnlyAvailable => 1
      case All => 0
    }
    def fromCode = {
      case 1 => OnlyAvailable.some
      case 0 => All.some
      case _ => none
    }
  }

  implicit object SearchFieldCaseCode extends CaseCode[Int, SearchField] {
    import SearchField._
    def toCode = {
      case Strict => 1
      case Broad => 0
    }
    def fromCode = {
      case 1 => Strict.some
      case 0 => Broad.some
      case _ => none
    }
  }

  implicit object CarrierCaseCode extends CaseCode[Int, Carrier] {
    import Carrier._
    def toCode = {
      case PC => 0
      case Mobile => 1
      case SP => 2
    }
    def fromCode = {
      case 0 => PC.some
      case 1 => Mobile.some
      case 2 => SP.some
      case _ => none
    }
  }

  implicit object ImageFlagCaseCode extends CaseCode[Int, ImageFlag] {
    import ImageFlag._
    def toCode = {
      case All => 0
      case OnlyHave => 1
    }
    def fromCode = {
      case 0 => All.some
      case 1 => OnlyHave.some
      case _ => none
    }
  }

  implicit object OrFlagCaseCode extends CaseCode[Int, OrFlag] {
    import OrFlag._
    def toCode = {
      case And => 0
      case Or => 1
    }
    def fromCode = {
      case 0 => And.some
      case 1 => Or.some
      case _ => none
    }
  }

  implicit object PurchaseTypeCaseCode extends CaseCode[Int, PurchaseType] {
    import PurchaseType._
    def toCode = {
      case NormalOrder => 0
      case StandingOrder => 1
      case DistributeOrder => 2
    }
    def fromCode = {
      case 0 => NormalOrder.some
      case 1 => StandingOrder.some
      case 2 => DistributeOrder.some
      case _ => none
    }
  }

  implicit object PostageFlagCaseCode extends CaseCode[Int, PostageFlag] {
    import PostageFlag._
    def toCode = {
      case All => 0
      case OnlyInclude => 1
    }
    def fromCode = {
      case 0 => All.some
      case 1 => OnlyInclude.some
      case _ => none
    }
  }

  implicit object CreditCardFlagCaseCode extends CaseCode[Int, CreditCardFlag] {
    import CreditCardFlag._
    def toCode = {
      case All => 0
      case OnlyAccept => 1
    }
    def fromCode = {
      case 0 => All.some
      case 1 => OnlyAccept.some
      case _ => none
    }
  }

  implicit object GiftFlagCaseCode extends CaseCode[Int, GiftFlag] {
    import GiftFlag._
    def toCode = {
      case All => 0
      case OnlyAccept => 1
    }
    def fromCode = {
      case 0 => All.some
      case 1 => OnlyAccept.some
      case _ => none
    }
  }

  implicit object HasReviewFlagCaseCode extends CaseCode[Int, HasReviewFlag] {
    import HasReviewFlag._
    def toCode = {
      case All => 0
      case OnlyHave => 1
    }
    def fromCode = {
      case 0 => All.some
      case 1 => OnlyHave.some
      case _ => none
    }
  }

  implicit object HasMovieFlagCaseCode extends CaseCode[Int, HasMovieFlag] {
    import HasMovieFlag._
    def toCode = {
      case All => 0
      case OnlyHave => 1
    }
    def fromCode = {
      case 0 => All.some
      case 1 => OnlyHave.some
      case _ => none
    }
  }

  implicit object PamphletFlagCaseCode extends CaseCode[Int, PamphletFlag] {
    import PamphletFlag._
    def toCode = {
      case All => 0
      case OnlyAccept => 1
    }
    def fromCode = {
      case 0 => All.some
      case 1 => OnlyAccept.some
      case _ => none
    }
  }

  implicit object AppointDeliveryDateFlagCaseCode extends CaseCode[Int, AppointDeliveryDateFlag] {
    import AppointDeliveryDateFlag._
    def toCode = {
      case All => 0
      case OnlyAccept => 1
    }
    def fromCode = {
      case 0 => All.some
      case 1 => OnlyAccept.some
      case _ => none
    }
  }
}

object ItemSearchCaseCodes extends ItemSearchCaseCodes


