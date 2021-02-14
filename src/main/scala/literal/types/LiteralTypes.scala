package literal.types

import shapeless.PolyDefns.->
import shapeless.PolyDefns.>->
import shapeless.syntax.singleton.mkSingletonOps

object LiteralTypes extends App {
  val one: 1 = 1                     // val declaration
  def foo(x: 1): Option[1] = Some(x) // param type, type arg
  def bar[T <: 1](t: T): T = t       // type parameter bound
  println(foo(1))
  println(bar(1))

  def foo2[T](t: T): t.type = t
  def foo3[T](t: T): T = t

  final val bar = foo2(23)
  final val bar2 = bar(1)
  final val bar3 = foo3(1)

  import shapeless._, record._, syntax.singleton._

  val wAuthor = Witness("author")
  val wTitle = Witness("title")
  val wId = Witness("id")
  val wPrice = Witness("price")

  type Book =
    (wTitle.T -> String) ::
      (wTitle.T  -> String) ::
      (wId.T     -> Int) ::
      (wPrice.T  -> Double) ::
      HNil

  //val book: Book =
  val book =
    ("author" ->> "Benjamin Pierce") ::
      ("title"  ->> "Types and Programming Languages") ::
      ("id"     ->>  262162091) ::
      ("price"  ->>  44.11) ::
      HNil
}

