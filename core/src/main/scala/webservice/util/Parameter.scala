package jp.co.rakuten.webservice.util

trait Parameter {
  def param: Seq[(String, String)]
}
