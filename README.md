rws-scala: Rakuten Web Service Scala Wrapper
========================================

[![Build Status](https://secure.travis-ci.org/nisshiee/rws-scala.png?branch=master)](http://travis-ci.org/nisshiee/rws-scala)

rws-scalaは、Scalaアプリケーションから楽天が提供しているAPIに簡単にアクセスすることができる関数群を提供します。


動作要件
----------------------------------------

- Scala 2.9.2


使い方
----------------------------------------

### 準備

1. [楽天ウェブサービスのドキュメントページ](http://webservice.rakuten.co.jp/)にて、アプリ登録を行ってください。
2. sbtプロジェクトを用意し、以下の設定をbuild.sbtに記述します。

```scala
scalaVersion := "2.9.2"

libraryDependencies ++= Seq(
   "org.nisshiee" %% "rws-scala" % "0.1.0"
  ,"org.nisshiee" %% "rws-scala-dispatch" % "0.1.0"
)
```

### コード記述

例:

```scala
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
```

詳細はWikiに記載していく予定です。


公開APIドキュメント
----------------------------------------

- [http://webservice.rakuten.co.jp/](http://webservice.rakuten.co.jp/)


ライセンス
----------------------------------------

- MIT License

