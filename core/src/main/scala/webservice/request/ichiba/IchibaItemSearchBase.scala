package jp.co.rakuten.webservice

/**
 * keyword, genreId, itemCodeのいずれかがセットされている事が保証された、リクエストパラメータ
 */
case class IchibaItemSearchBase private (
   keyword: Option[String]
  ,genreId: Option[String]
  ,itemCode: Option[String]
) extends Paramater {
  def param: Seq[(String, String)] = Seq(
     keyword map { "keyword" -> _ }
    ,genreId map { "genreId" -> _ }
    ,itemCode map { "itemCode" -> _ }
  ).flatten

}

object IchibaItemSearchBase {

  def |(keyword: String) = SomeK(keyword)
  def ||(genreId: String): SomeKG = SomeKG(None, Some(genreId))
  def ||(genreId: Long): SomeKG = ||(genreId.toString)
  def |||(itemCode: String) = SomeKGI(None, None, Some(itemCode))

  val _k = | _
  val _keyword = | _
  def _g(genreId: String): SomeKG = ||(genreId)
  def _genreId(genreId: String): SomeKG = ||(genreId)
  def _g(genreId: Long): SomeKG = ||(genreId)
  def _genreId(genreId: Long): SomeKG = ||(genreId)
  val _i = ||| _
  val _itemCode = ||| _

  case class SomeK private[IchibaItemSearchBase] (keyword: String) {
    def |(genreId: String): SomeKG = SomeKG(Some(keyword), Some(genreId))
    def |(genreId: Long): SomeKG = SomeKG(Some(keyword), Some(genreId.toString))
    def ||(itemCode: String) = SomeKGI(Some(keyword), None, Some(itemCode))
    def ||| = IchibaItemSearchBase(Some(keyword), None, None)

    def _g(genreId: String): SomeKG = |(genreId)
    def _genreId(genreId: String): SomeKG = |(genreId)
    def _g(genreId: Long): SomeKG = |(genreId)
    def _genreId(genreId: Long): SomeKG = |(genreId)
    val _i = || _
    val _itemCode = || _
  }
  case object NoneK {
    def |(genreId: String): SomeKG = SomeKG(None, Some(genreId))
    def |(genreId: Long): SomeKG = SomeKG(None, Some(genreId.toString))
    def ||(itemCode: String) = SomeKGI(None, None, Some(itemCode))

    def _g(genreId: String): SomeKG = |(genreId)
    def _genreId(genreId: String): SomeKG = |(genreId)
    def _g(genreId: Long): SomeKG = |(genreId)
    def _genreId(genreId: Long): SomeKG = |(genreId)
    val _i = || _
    val _itemCode = || _
  }

  case class SomeKG private[IchibaItemSearchBase] (
     keyword: Option[String]
    ,genreId: Option[String]
  ) {
    def |(itemCode: String) = SomeKGI(keyword, genreId, Some(itemCode))
    def || = IchibaItemSearchBase(keyword, genreId, None)

    val _i = | _
    val _itemCode = | _
  }
  case class NoneKG private[IchibaItemSearchBase] (
     keyword: Option[String]
    ,genreId: Option[String]
  ) {
    def |(itemCode: String) = SomeKGI(keyword, genreId, Some(itemCode))

    val _i = | _
    val _itemCode = | _
  }
  case class SomeKGI private[IchibaItemSearchBase] (
     keyword: Option[String]
    ,genreId: Option[String]
    ,itemCode: Option[String]
  ) {
    def | = IchibaItemSearchBase(keyword, genreId, itemCode)
  }
}
