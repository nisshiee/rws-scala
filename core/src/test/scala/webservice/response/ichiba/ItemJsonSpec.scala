package rwsscala

import org.specs2._, matcher.DataTables
import net.liftweb.json._, Implicits._, JsonDSL._
import scalaz._, Scalaz._

class ItemJsonSpec extends Specification with DataTables { def is =

  "ItemJsonSpec"                                                                                    ^
    "imageUrlExtraction"                                                                            ^
      "{ \"imageUrl\": string } => string 抽出 成功パターン"                                        ! e1^
      "{ \"imageUrl\": string } => string 抽出 失敗パターン"                                        ! e2^
                                                                                                    p^
    "itemExtraction"                                                                                ^
      "{ \"Item\": object } => object 抽出 成功パターン"                                            ! e3^
      "{ \"Item\": object } => object 抽出 失敗パターン"                                            ! e4^
                                                                                                    p^
    "itemRename"                                                                                    ^
      "{ \"Items\": array } => { \"items\": array } rename 成功パターン"                            ! e5^
      "{ \"Items\": array } => { \"items\": array } rename 失敗パターン"                            ! e6^
                                                                                                    p^
    "itemTransformation"                                                                            ^
      "成功パターン"                                                                                ! e7^
      "失敗パターン"                                                                                ! e8^
      "JValue.transformを適用して想定通りの変換がされる"                                            ! e9^
                                                                                                    p^
                                                                                                    end

  val input1: JObject = ("imageUrl", "url")
  val result1: JValue = "url"

  def e1 =
    ItemJson.imageUrlExtraction.lift(input1) must beSome.which(result1 ==)

  def e2 =
    "input"                                  | "result" |
    (("hoge", "url"): JObject)               ! "none"   |
    (("imageUrls", "url"): JObject)          ! "none"   |
    (("imageUrl", 1): JObject)               ! "none"   |
    (("imageUrl", JNothing): JObject)        ! "none"   |
    (("hoge", ("imageUrl", "url")): JObject) ! "none"   |
    (List("imageUrl" -> "url"): JArray)      ! "none"   |> { (input, _) =>
      ItemJson.imageUrlExtraction.lift(input) must beNone
    }

  val result2: JObject = ("itemName" -> "name") ~ ("itemPrice" -> "1210")
  val input2: JObject = ("Item", result2)

  def e3 =
    ItemJson.itemExtraction.lift(input2) must beSome.which(result2 ==)

  def e4 = {
    val obj: JObject = Map("itemName" -> "name", "itemPrice" -> "1210")
    "input"                            | "result" |
    (("hoge", obj): JObject)           ! "none"   | 
    (("item", obj): JObject)           ! "none"   | 
    (("Item", "item"): JObject)        ! "none"   | 
    (("Item", JNothing): JObject)      ! "none"   | 
    (("hoge", ("Item", obj)): JObject) ! "none"   | 
    (List("Item" -> obj): JArray)      ! "none"   |> { (input, _) =>
      ItemJson.itemExtraction.lift(input) must beNone
    } 
  }

  val (input3, result3) = {
    val ary3: JArray = List(("itemName" -> "name") ~ ("itemPrice" -> "1210"))
    (JField("Items", ary3), JField("items", ary3))
  } 

  def e5 =
    ItemJson.itemsRename.lift(input3) must beSome.which(result3 ==)

  def e6 = {
    val ary: JArray = List(Map("itemName" -> "name", "itemPrice" -> "1210"))
    "input"                             | "result" |
    JField("hoge", ary)                 ! "none"   | 
    JField("Item", ary)                 ! "none"   | 
    JField("Items", "items")            ! "none"   | 
    JField("Items", JNothing)           ! "none"   | 
    (("hoge", ("Items", ary)): JObject) ! "none"   | 
    (List("Items" -> ary): JArray)      ! "none"   |> { (input, _) =>
      ItemJson.itemsRename.lift(input) must beNone
    }
  }

  def e7 =
    "input" | "result" |
    input1  ! result1  |
    input2  ! result2  |
    input3  ! result3  |> { (input, result) =>
      ItemJson.itemTransformation.lift(input) must beSome.which(result ==)
    }

  def e8 = {
    val obj: JObject = Map("itemName" -> "name", "itemPrice" -> "1210")
    val ary: JArray = List(Map("itemName" -> "name", "itemPrice" -> "1210"))
    "input"                                  | "result" |
    (("hoge", "url"): JObject)               ! "none"   |
    (("imageUrls", "url"): JObject)          ! "none"   |
    (("imageUrl", 1): JObject)               ! "none"   |
    (("imageUrl", JNothing): JObject)        ! "none"   |
    (("hoge", ("imageUrl", "url")): JObject) ! "none"   |
    (List("imageUrl" -> "url"): JArray)      ! "none"   |
    (("hoge", obj): JObject)                 ! "none"   | 
    (("item", obj): JObject)                 ! "none"   | 
    (("Item", "item"): JObject)              ! "none"   | 
    (("Item", JNothing): JObject)            ! "none"   | 
    (("hoge", ("Item", obj)): JObject)       ! "none"   | 
    (List("Item" -> obj): JArray)            ! "none"   |
    JField("hoge", ary)                      ! "none"   | 
    JField("Item", ary)                      ! "none"   | 
    JField("Items", "items")                 ! "none"   | 
    JField("Items", JNothing)                ! "none"   | 
    (("hoge", ("Items", ary)): JObject)      ! "none"   | 
    (List("Items" -> ary): JArray)           ! "none"   |> { (input, _) =>
      ItemJson.itemTransformation.lift(input) must beNone
    }
  }

  def e9 = {
    val input: JObject =
      ("page", 1) ~ 
      ("Items",
        List(
          ("Item",
            ("itemName", "name1") ~
            ("smallImageUrls",
              List(
                 ("imageUrl", "url1")
                ,("imageUrl", "url2")
              )
            ) ~
            ("mediumImageUrls",
              List(
                 ("imageUrl", "url3")
                ,("imageUrl", "url4")
              )
            )
          )
          ,("Item",
            ("itemName", "name2") ~
            ("smallImageUrls",
              List(
                 ("imageUrl", "url5")
                ,("imageUrl", "url6")
              )
            ) ~
            ("mediumImageUrls",
              List(
                 ("imageUrl", "url7")
                ,("imageUrl", "url8")
              )
            )
          )
        )
      )
    val result: JObject =
      ("page", 1) ~ 
      ("items",
        List(
          ("itemName", "name1") ~
          ("smallImageUrls",
            List[JValue](
               "url1"
              ,"url2"
            )
          ) ~
          ("mediumImageUrls",
            List[JValue](
               "url3"
              ,"url4"
            )
          )
          ,("itemName", "name2") ~
          ("smallImageUrls",
            List[JValue](
               "url5"
              ,"url6"
            )
          ) ~
          ("mediumImageUrls",
            List[JValue](
               "url7"
              ,"url8"
            )
          )
        )
      )
    input.transform(ItemJson.itemTransformation) must equalTo(result)
  }
}

