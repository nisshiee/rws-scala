package jp.co.rakuten.webservice.dispatch

import jp.co.rakuten.webservice.httptrait._
import dispatch._

object DispatchHttps extends RwsHttps {

  override def get(
     domain: String
    ,path: String
    ,params: Map[String, String]
  ): Either[ConnectionError, Response] =
    Http(:/(domain).secure / path <<? params OK as.String).either.right.map {
      Response(200, _)
    }.left.map {
      case e: Throwable => ConnectionError(e.getMessage)
    }()
}
