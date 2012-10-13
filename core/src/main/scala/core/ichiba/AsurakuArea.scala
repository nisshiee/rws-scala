package rwsscala.ichiba

sealed trait AsurakuArea {
  def name: String
  def code: Int
}

object AsurakuArea {

  case object AllArea extends AsurakuArea {
    val name = "全地域"
    val code = 0
  }
  case object AllHokkaido extends AsurakuArea {
    val name = "北海道エリアのすべて"
    val code = 100
  }
  case object Hokkaido extends AsurakuArea {
    val name = "北海道"
    val code = 1
  }
  case object AllTohoku extends AsurakuArea {
    val name = "東北エリアのすべて"
    val code = 101
  }
  case object Aomori extends AsurakuArea {
    val name = "青森県"
    val code = 2
  }
  case object Iwate extends AsurakuArea {
    val name = "岩手県"
    val code = 3
  }
  case object Miyagi extends AsurakuArea {
    val name = "宮城県"
    val code = 4
  }
  case object Akita extends AsurakuArea {
    val name = "秋田県"
    val code = 5
  }
  case object Yamagata extends AsurakuArea {
    val name = "山形県"
    val code = 6
  }
  case object Fukushima extends AsurakuArea {
    val name = "福島県"
    val code = 7
  }
  case object AllKanto extends AsurakuArea {
    val name = "関東エリアのすべて"
    val code = 102
  }
  case object Ibaraki extends AsurakuArea {
    val name = "茨城県"
    val code = 8
  }
  case object Tochigi extends AsurakuArea {
    val name = "栃木県"
    val code = 9
  }
  case object Gunma extends AsurakuArea {
    val name = "群馬県"
    val code = 10
  }
  case object Saitama extends AsurakuArea {
    val name = "埼玉県"
    val code = 11
  }
  case object Chiba extends AsurakuArea {
    val name = "千葉県"
    val code = 12
  }
  case object Tokyo extends AsurakuArea {
    val name = "東京都"
    val code = 13
  }
  case object Kanagawa extends AsurakuArea {
    val name = "神奈川県"
    val code = 14
  }
  case object AllKoshinetsu extends AsurakuArea {
    val name = "甲信越エリアのすべて"
    val code = 103
  }
  case object Niigata extends AsurakuArea {
    val name = "新潟県"
    val code = 15
  }
  case object Yamanashi extends AsurakuArea {
    val name = "山梨県"
    val code = 19
  }
  case object Nagano extends AsurakuArea {
    val name = "長野県"
    val code = 20
  }
  case object AllHokuriku extends AsurakuArea {
    val name = "北陸エリアのすべて"
    val code = 104
  }
  case object Toyama extends AsurakuArea {
    val name = "富山県"
    val code = 16
  }
  case object Ishikawa extends AsurakuArea {
    val name = "石川県"
    val code = 17
  }
  case object Fukui extends AsurakuArea {
    val name = "福井県"
    val code = 18
  }
  case object AllTokai extends AsurakuArea {
    val name = "東海エリアのすべて"
    val code = 105
  }
  case object Gifu extends AsurakuArea {
    val name = "岐阜県"
    val code = 21
  }
  case object Shizuoka extends AsurakuArea {
    val name = "静岡県"
    val code = 22
  }
  case object Aichi extends AsurakuArea {
    val name = "愛知県"
    val code = 23
  }
  case object Mie extends AsurakuArea {
    val name = "三重県"
    val code = 24
  }
  case object AllKansai extends AsurakuArea {
    val name = "関西エリアのすべて"
    val code = 106
  }
  case object Shiga extends AsurakuArea {
    val name = "滋賀県"
    val code = 25
  }
  case object Kyoto extends AsurakuArea {
    val name = "京都府"
    val code = 26
  }
  case object Osaka extends AsurakuArea {
    val name = "大阪府"
    val code = 27
  }
  case object Hyogo extends AsurakuArea {
    val name = "兵庫県"
    val code = 28
  }
  case object Nara extends AsurakuArea {
    val name = "奈良県"
    val code = 29
  }
  case object Wakayama extends AsurakuArea {
    val name = "和歌山県"
    val code = 30
  }
  case object AllChugoku extends AsurakuArea {
    val name = "中国エリアのすべて"
    val code = 107
  }
  case object Tottori extends AsurakuArea {
    val name = "鳥取県"
    val code = 31
  }
  case object Shimane extends AsurakuArea {
    val name = "島根県"
    val code = 32
  }
  case object Okayama extends AsurakuArea {
    val name = "岡山県"
    val code = 33
  }
  case object Hiroshima extends AsurakuArea {
    val name = "広島県"
    val code = 34
  }
  case object Yamaguchi extends AsurakuArea {
    val name = "山口県"
    val code = 35
  }
  case object AllShikoku extends AsurakuArea {
    val name = "四国エリアのすべて"
    val code = 108
  }
  case object Tokushima extends AsurakuArea {
    val name = "徳島県"
    val code = 36
  }
  case object Kagawa extends AsurakuArea {
    val name = "香川県"
    val code = 37
  }
  case object Ehime extends AsurakuArea {
    val name = "愛媛県"
    val code = 38
  }
  case object Kochi extends AsurakuArea {
    val name = "高知県"
    val code = 39
  }
  case object AllKyushu extends AsurakuArea {
    val name = "九州エリアのすべて"
    val code = 109
  }
  case object Fukuoka extends AsurakuArea {
    val name = "福岡県"
    val code = 40
  }
  case object Saga extends AsurakuArea {
    val name = "佐賀県"
    val code = 41
  }
  case object Nagasaki extends AsurakuArea {
    val name = "長崎県"
    val code = 42
  }
  case object Kumamoto extends AsurakuArea {
    val name = "熊本県"
    val code = 43
  }
  case object Oita extends AsurakuArea {
    val name = "大分県"
    val code = 44
  }
  case object Miyazaki extends AsurakuArea {
    val name = "宮崎県"
    val code = 45
  }
  case object Kagoshima extends AsurakuArea {
    val name = "鹿児島県"
    val code = 46
  }
  case object AllOkinawa extends AsurakuArea {
    val name = "沖縄エリアのすべて"
    val code = 110
  }
  case object Okinawa extends AsurakuArea {
    val name = "沖縄県"
    val code = 47
  }

  def parseOpt: String => Option[AsurakuArea] = {
    case "全地域" => Some(AllArea)
    case "北海道エリアのすべて" => Some(AllHokkaido)
    case "北海道" => Some(Hokkaido)
    case "東北エリアのすべて" => Some(AllTohoku)
    case "青森県" => Some(Aomori)
    case "岩手県" => Some(Iwate)
    case "宮城県" => Some(Miyagi)
    case "秋田県" => Some(Akita)
    case "山形県" => Some(Yamagata)
    case "福島県" => Some(Fukushima)
    case "関東エリアのすべて" => Some(AllKanto)
    case "茨城県" => Some(Ibaraki)
    case "栃木県" => Some(Tochigi)
    case "群馬県" => Some(Gunma)
    case "埼玉県" => Some(Saitama)
    case "千葉県" => Some(Chiba)
    case "東京都" => Some(Tokyo)
    case "神奈川県" => Some(Kanagawa)
    case "甲信越エリアのすべて" => Some(AllKoshinetsu)
    case "新潟県" => Some(Niigata)
    case "山梨県" => Some(Yamanashi)
    case "長野県" => Some(Nagano)
    case "北陸エリアのすべて" => Some(AllHokuriku)
    case "富山県" => Some(Toyama)
    case "石川県" => Some(Ishikawa)
    case "福井県" => Some(Fukui)
    case "東海エリアのすべて" => Some(AllTokai)
    case "岐阜県" => Some(Gifu)
    case "静岡県" => Some(Shizuoka)
    case "愛知県" => Some(Aichi)
    case "三重県" => Some(Mie)
    case "関西エリアのすべて" => Some(AllKansai)
    case "滋賀県" => Some(Shiga)
    case "京都府" => Some(Kyoto)
    case "大阪府" => Some(Osaka)
    case "兵庫県" => Some(Hyogo)
    case "奈良県" => Some(Nara)
    case "和歌山県" => Some(Wakayama)
    case "中国エリアのすべて" => Some(AllChugoku)
    case "鳥取県" => Some(Tottori)
    case "島根県" => Some(Shimane)
    case "岡山県" => Some(Okayama)
    case "広島県" => Some(Hiroshima)
    case "山口県" => Some(Yamaguchi)
    case "四国エリアのすべて" => Some(AllShikoku)
    case "徳島県" => Some(Tokushima)
    case "香川県" => Some(Kagawa)
    case "愛媛県" => Some(Ehime)
    case "高知県" => Some(Kochi)
    case "九州エリアのすべて" => Some(AllKyushu)
    case "福岡県" => Some(Fukuoka)
    case "佐賀県" => Some(Saga)
    case "長崎県" => Some(Nagasaki)
    case "熊本県" => Some(Kumamoto)
    case "大分県" => Some(Oita)
    case "宮崎県" => Some(Miyazaki)
    case "鹿児島県" => Some(Kagoshima)
    case "沖縄エリアのすべて" => Some(AllOkinawa)
    case "沖縄県" => Some(Okinawa)
    case _ => None
  }
}
