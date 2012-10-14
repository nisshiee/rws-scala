// コアパッケージのimport
import rwsscala._, rwsscala.ichiba._

object MyFirstRws extends App {

  // 使用するHTTP実装をimplicit valueとして保持
  implicit val https = rwsscala.dispatch.DispatchHttps

  // あなたのアプリケーション情報をimplicit valueとして保持
  implicit val app = ApplicationDetail("your applicationId", "your affiliateId")

  // 市場商品検索APIで「うどん」を検索
  val result = ItemSearch(ItemSearchBase | "うどん" |||)

  // 成功したら商品名リストを、失敗したらエラー内容を標準出力に表示
  result.fold(
     { e => println(e) }
    ,{ case ItemSearchResult(_, _, _, _, _, _, _, items) => items.map(_.name).foreach(println) }
  )

  // HTTP実装にDispatchHttpsを使用した場合は、通信スレッドをシャットダウンする必要があります
  https.shutdown
}
