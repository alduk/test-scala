package shapeless.test

import shapeless.ops.hlist
import shapeless.ops.hlist.Mapper

object SetNoneToCaseClassFields extends App{
  import shapeless._
  import shapeless.labelled._

  def setOptionFieldsToNone[A, KeyRepr <: HList, V <: HList, Repr <: HList](obj: A)(
    implicit gen: LabelledGeneric.Aux[A, Repr],
    traversable: hlist.ToTraversable.Aux[KeyRepr, List, Symbol],
    mapper: Mapper.Aux[setNone.type, Repr, Repr]
  ): A = {
    val hlist = gen.to(obj)
    gen.from(mapper(hlist))
  }

  object setNone extends Poly1 {

    implicit def setNoneOption[K <: Symbol, T](implicit
      name: Witness.Aux[K],
                                               keys: Set[Symbol]
    ): Case.Aux[FieldType[K, Option[T]], FieldType[K, Option[T]]] =
      at[FieldType[K, Option[T]]]{f =>
        if(keys.contains(name.value))
          None.asInstanceOf[FieldType[K, Option[T]]]
        else
          f
      }
    implicit def genericCase[T]: Case.Aux[T,T] = at[T](identity)
  }

  def getKeys:Set[Symbol] = Set(Symbol("a"), Symbol("c"))

  case class TestClass(a: Option[String], b: Option[String],  c: String)
  val test = TestClass(Some("a"), Some("B"), "c")
  implicit val keys = getKeys
  val res = setOptionFieldsToNone(test)
  println(res)
}
