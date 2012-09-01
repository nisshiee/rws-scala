package jp.co.rakuten.webservice.httptrait

import jp.co.rakuten.webservice.ApiError

case class Response(code: Int, body: String)

case class ConnectionError(mes: String) extends ApiError
