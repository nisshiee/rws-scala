package jp.co.rakuten.webservice

sealed trait OverseaArea {
  def name: String
  def code: String
}
case object AllCountry extends OverseaArea {
  val name = "ワールドワイド（すべての国）"
  val code = "ALL"
}
case object UnitedStates extends OverseaArea {
  val name = "アメリカ"
  val code = "US"
}
case object Argentina extends OverseaArea {
  val name = "アルゼンチン"
  val code = "AR"
}
case object Brazil extends OverseaArea {
  val name = "ブラジル"
  val code = "BR"
}
case object Canada extends OverseaArea {
  val name = "カナダ"
  val code = "CA"
}
case object Mexico extends OverseaArea {
  val name = "メキシコ"
  val code = "MX"
}
case object Austria extends OverseaArea {
  val name = "オーストリア"
  val code = "AT"
}
case object Belgium extends OverseaArea {
  val name = "ベルギー"
  val code = "BE"
}
case object Denmark extends OverseaArea {
  val name = "デンマーク"
  val code = "DK"
}
case object France extends OverseaArea {
  val name = "フランス"
  val code = "FR"
}
case object Germany extends OverseaArea {
  val name = "ドイツ"
  val code = "DE"
}
case object Greece extends OverseaArea {
  val name = "ギリシャ"
  val code = "GR"
}
case object Italy extends OverseaArea {
  val name = "イタリア"
  val code = "IT"
}
case object Morocco extends OverseaArea {
  val name = "モロッコ"
  val code = "MA"
}
case object Netherlands extends OverseaArea {
  val name = "オランダ"
  val code = "NL"
}
case object Poland extends OverseaArea {
  val name = "ポーランド"
  val code = "PL"
}
case object Portugal extends OverseaArea {
  val name = "ポルトガル"
  val code = "PT"
}
case object Russia extends OverseaArea {
  val name = "ロシア"
  val code = "RU"
}
case object Spain extends OverseaArea {
  val name = "スペイン"
  val code = "ES"
}
case object Sweden extends OverseaArea {
  val name = "スウェーデン"
  val code = "SE"
}
case object Switzerland extends OverseaArea {
  val name = "スイス"
  val code = "CH"
}
case object Turkey extends OverseaArea {
  val name = "トルコ"
  val code = "TR"
}
case object UnitedKingdom extends OverseaArea {
  val name = "英国"
  val code = "GB"
}
case object Australia extends OverseaArea {
  val name = "オーストラリア"
  val code = "AU"
}
case object China extends OverseaArea {
  val name = "中国"
  val code = "CN"
}
case object HongKong extends OverseaArea {
  val name = "香港"
  val code = "HK"
}
case object India extends OverseaArea {
  val name = "インド"
  val code = "IN"
}
case object Indonesia extends OverseaArea {
  val name = "インドネシア"
  val code = "ID"
}
case object Korea extends OverseaArea {
  val name = "韓国"
  val code = "KR"
}
case object Malaysia extends OverseaArea {
  val name = "マレーシア"
  val code = "MY"
}
case object NewZealand extends OverseaArea {
  val name = "ニュージーランド"
  val code = "NZ"
}
case object Philippines extends OverseaArea {
  val name = "フィリピン"
  val code = "PH"
}
case object Singapore extends OverseaArea {
  val name = "シンガポール"
  val code = "SG"
}
case object Taiwan extends OverseaArea {
  val name = "台湾"
  val code = "TW"
}
case object Thailand extends OverseaArea {
  val name = "タイ"
  val code = "TH"
}
case object VietNam extends OverseaArea {
  val name = "ベトナム"
  val code = "VN"
}

