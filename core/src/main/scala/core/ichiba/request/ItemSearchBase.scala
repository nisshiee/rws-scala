package rwsscala.ichiba

import rwsscala.util._

/**
 * keyword, genreId, itemCodeのいずれかがセットされている事が保証された、リクエストパラメータ
 */
case class ItemSearchBase private (
   keyword: Option[String]
  ,genreId: Option[Long]
  ,itemCode: Option[String]
) extends Parameter {
  def param: Seq[(String, String)] = Seq(
     keyword map { "keyword" -> _ }
    ,genreId map { "genreId" -> _.toString }
    ,itemCode map { "itemCode" -> _ }
  ).flatten

}

object ItemSearchBase {

  def |(keyword: String) = SomeK(keyword)
  def ||(genreId: Long): SomeKG = SomeKG(None, Some(genreId))
  def |||(itemCode: String) = SomeKGI(None, None, Some(itemCode))

  val _k = | _
  val _keyword = | _
  def _g(genreId: Long): SomeKG = ||(genreId)
  def _genreId(genreId: Long): SomeKG = ||(genreId)
  val _i = ||| _
  val _itemCode = ||| _

  case class SomeK private[ItemSearchBase] (keyword: String) {
    def |(genreId: Long): SomeKG = SomeKG(Some(keyword), Some(genreId))
    def ||(itemCode: String) = SomeKGI(Some(keyword), None, Some(itemCode))
    def ||| = ItemSearchBase(Some(keyword), None, None)

    def _g(genreId: Long): SomeKG = |(genreId)
    def _genreId(genreId: Long): SomeKG = |(genreId)
    val _i = || _
    val _itemCode = || _
  }
  case object NoneK {
    def |(genreId: Long): SomeKG = SomeKG(None, Some(genreId))
    def ||(itemCode: String) = SomeKGI(None, None, Some(itemCode))

    def _g(genreId: Long): SomeKG = |(genreId)
    def _genreId(genreId: Long): SomeKG = |(genreId)
    val _i = || _
    val _itemCode = || _
  }

  case class SomeKG private[ItemSearchBase] (
     keyword: Option[String]
    ,genreId: Option[Long]
  ) {
    def |(itemCode: String) = SomeKGI(keyword, genreId, Some(itemCode))
    def || = ItemSearchBase(keyword, genreId, None)

    val _i = | _
    val _itemCode = | _
  }
  case object NoneKG {
    def |(itemCode: String) = SomeKGI(None, None, Some(itemCode))

    val _i = | _
    val _itemCode = | _
  }
  case class SomeKGI private[ItemSearchBase] (
     keyword: Option[String]
    ,genreId: Option[Long]
    ,itemCode: Option[String]
  ) {
    def | = ItemSearchBase(keyword, genreId, itemCode)
  }
}
