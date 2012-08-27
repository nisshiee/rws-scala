package jp.co.rakuten.webservice.httptrait

case class Response(code: Int, body: String)

case class ConnectionError(mes: String)
