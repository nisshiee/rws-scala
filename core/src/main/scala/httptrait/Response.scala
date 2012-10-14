package rwsscala.httptrait

import rwsscala.ApiError

case class Response(code: Int, body: String)

case class ConnectionError(mes: String) extends ApiError
