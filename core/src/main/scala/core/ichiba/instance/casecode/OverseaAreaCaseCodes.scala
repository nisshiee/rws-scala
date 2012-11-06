package rwsscala.ichiba

import rwsscala._
import rwsscala.util._

import scalaz._, Scalaz._

object OverseaAreaCaseCodes {

  object ParameterCaseCode extends CaseCode[String, OverseaArea] {
    import OverseaArea._
    def toCode = {
      case AllCountry => "ALL"
      case UnitedStates => "US"
      case Argentina => "AR"
      case Brazil => "BR"
      case Canada => "CA"
      case Mexico => "MX"
      case Austria => "AT"
      case Belgium => "BE"
      case Denmark => "DK"
      case France => "FR"
      case Germany => "DE"
      case Greece => "GR"
      case Italy => "IT"
      case Morocco => "MA"
      case Netherlands => "NL"
      case Poland => "PL"
      case Portugal => "PT"
      case Russia => "RU"
      case Spain => "ES"
      case Sweden => "SE"
      case Switzerland => "CH"
      case Turkey => "TR"
      case UnitedKingdom => "GB"
      case Australia => "AU"
      case China => "CN"
      case HongKong => "HK"
      case India => "IN"
      case Indonesia => "ID"
      case Korea => "KR"
      case Malaysia => "MY"
      case NewZealand => "NZ"
      case Philippines => "PH"
      case Singapore => "SG"
      case Taiwan => "TW"
      case Thailand => "TH"
      case VietNam => "VN"
    }

    def fromCode = {
      case "ALL" => AllCountry.some
      case "US" => UnitedStates.some
      case "AR" => Argentina.some
      case "BR" => Brazil.some
      case "CA" => Canada.some
      case "MX" => Mexico.some
      case "AT" => Austria.some
      case "BE" => Belgium.some
      case "DK" => Denmark.some
      case "FR" => France.some
      case "DE" => Germany.some
      case "GR" => Greece.some
      case "IT" => Italy.some
      case "MA" => Morocco.some
      case "NL" => Netherlands.some
      case "PL" => Poland.some
      case "PT" => Portugal.some
      case "RU" => Russia.some
      case "ES" => Spain.some
      case "SE" => Sweden.some
      case "CH" => Switzerland.some
      case "TR" => Turkey.some
      case "GB" => UnitedKingdom.some
      case "AU" => Australia.some
      case "CN" => China.some
      case "HK" => HongKong.some
      case "IN" => India.some
      case "ID" => Indonesia.some
      case "KR" => Korea.some
      case "MY" => Malaysia.some
      case "NZ" => NewZealand.some
      case "PH" => Philippines.some
      case "SG" => Singapore.some
      case "TW" => Taiwan.some
      case "TH" => Thailand.some
      case "VN" => VietNam.some
      case _ => none
    }
  }

  object ResponseCaseCode extends CaseCode[String, OverseaArea] {
    import OverseaArea._
    def toCode = {
      case AllCountry => "ワールドワイド"
      case UnitedStates => "アメリカ"
      case Argentina => "アルゼンチン"
      case Brazil => "ブラジル"
      case Canada => "カナダ"
      case Mexico => "メキシコ"
      case Austria => "オーストリア"
      case Belgium => "ベルギー"
      case Denmark => "デンマーク"
      case France => "フランス"
      case Germany => "ドイツ"
      case Greece => "ギリシャ"
      case Italy => "イタリア"
      case Morocco => "モロッコ"
      case Netherlands => "オランダ"
      case Poland => "ポーランド"
      case Portugal => "ポルトガル"
      case Russia => "ロシア"
      case Spain => "スペイン"
      case Sweden => "スウェーデン"
      case Switzerland => "スイス"
      case Turkey => "トルコ"
      case UnitedKingdom => "英国"
      case Australia => "オーストラリア"
      case China => "中国"
      case HongKong => "香港"
      case India => "インド"
      case Indonesia => "インドネシア"
      case Korea => "韓国"
      case Malaysia => "マレーシア"
      case NewZealand => "ニュージーランド"
      case Philippines => "フィリピン"
      case Singapore => "シンガポール"
      case Taiwan => "台湾"
      case Thailand => "タイ"
      case VietNam => "ベトナム"
    }

    def fromCode = {
      case "ワールドワイド" => AllCountry.some
      case "アメリカ" => UnitedStates.some
      case "アルゼンチン" => Argentina.some
      case "ブラジル" => Brazil.some
      case "カナダ" => Canada.some
      case "メキシコ" => Mexico.some
      case "オーストリア" => Austria.some
      case "ベルギー" => Belgium.some
      case "デンマーク" => Denmark.some
      case "フランス" => France.some
      case "ドイツ" => Germany.some
      case "ギリシャ" => Greece.some
      case "イタリア" => Italy.some
      case "モロッコ" => Morocco.some
      case "オランダ" => Netherlands.some
      case "ポーランド" => Poland.some
      case "ポルトガル" => Portugal.some
      case "ロシア" => Russia.some
      case "スペイン" => Spain.some
      case "スウェーデン" => Sweden.some
      case "スイス" => Switzerland.some
      case "トルコ" => Turkey.some
      case "英国" => UnitedKingdom.some
      case "オーストラリア" => Australia.some
      case "中国" => China.some
      case "香港" => HongKong.some
      case "インド" => India.some
      case "インドネシア" => Indonesia.some
      case "韓国" => Korea.some
      case "マレーシア" => Malaysia.some
      case "ニュージーランド" => NewZealand.some
      case "フィリピン" => Philippines.some
      case "シンガポール" => Singapore.some
      case "台湾" => Taiwan.some
      case "タイ" => Thailand.some
      case "ベトナム" => VietNam.some
      case _ => none
    }
  }
}
