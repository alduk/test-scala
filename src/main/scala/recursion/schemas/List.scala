package recursion.schemas

sealed trait List
case class Cons(
                 head: Int,
                 tail: List
               ) extends List

case object Nil extends List

object ListO {
  type ListF[A] = Option[(Int, A)]

  implicit val listFFunctor = new Functor[ListF] {
    override def map[A, B](list: ListF[A], f: A => B): Option[(Int, B)] =
      list match {
        case Some((head, tail)) => Some((head, f(tail)))
        case None               => None
      }
  }

  val projectList: List => ListF[List] = {
    case Cons(head, tail) => Some((head, tail))
    case Nil              => None
  }

  case class List2(value: ListF[List2])

  type FixedList = Fix[ListF]
}