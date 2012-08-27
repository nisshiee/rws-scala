package jp.co.rakuten.webservice.httptrait

trait Http {

  def get(domain: String, path: String, params: Map[String, String]): Either[ConnectionError, Response]
}
