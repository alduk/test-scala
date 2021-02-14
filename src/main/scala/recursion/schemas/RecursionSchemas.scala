package recursion.schemas

//   An introduction to recursion schemes
//   https://nrinaudo.github.io/recschemes/
object RecursionSchemas extends App{

  import TreeF._
  import ListO._

  def map[F[_], A, B](fa: F[A], f: A => B)(implicit functor: Functor[F]): F[B] = functor.map(fa, f)

  val ints: List = Cons(3, Cons(2, Cons(1, Nil)))

  val ints2: List2 =
    List2(Some((3,
      List2(Some((2,
        List2(Some((1,
          List2(None)
        )))
      )))
    )))

  val fixedInts: FixedList =
    Fix[ListF](Some((3,
      Fix[ListF](Some((2,
        Fix[ListF](Some((1,
          Fix[ListF](None)
        )))
      )))
    )))

  val intTree =
    Node(
      Node(
        Node(Leaf, 1, Leaf),
        2,
        Node(Leaf, 3, Leaf)
      ),
      4,
      Leaf
    )

  val fixedIntTree: FixedTree =
    Fix[TreeF](NodeF(
      Fix[TreeF](NodeF(
        Fix[TreeF](NodeF(Fix[TreeF](LeafF), 1, Fix[TreeF](LeafF))),
        2,
        Fix[TreeF](NodeF(Fix[TreeF](LeafF), 3, Fix[TreeF](LeafF)))
      )),
      4,
      Fix[TreeF](LeafF)
    ))

  //project Tree       =>  TreeF[Tree]
  //algebra TreeF[Int] =>  Int
  //loop    Tree       =>  Int
  def cata[F[_]: Functor, A, B](algebra: F[A] => A, project: B => F[B]): B => A = {
    def loop(state: B): A = {
      println(s"loop:$state")
      algebra(map(project(state), loop))
    }
    loop
  }

  def cataFix[F[_]: Functor, A](algebra: F[A] => A): Fix[F] => A = {
    def loop(state: Fix[F]): A = algebra(map(state.value, loop))
    loop
  }

  val heightAlgebra: TreeF[Int] => Int = {
    case n@NodeF(left, _, right) =>
      println(s"heightAlgebra:$n")
      1 + math.max(left, right)
    case LeafF                 =>
      println(s"heightAlgebra:$LeafF")
      0
  }

  val productAlgebra: ListF[Int] => Int = {
    case Some((head, tailProduct)) => head * tailProduct
    case None                      => 1
  }

  val mksStringAlgebra: ListF[String] => String = {
    case Some((head, tailProduct)) => s"$head::$tailProduct"
    case None                      => "nil"
  }

  val height: Tree => Int = cata[TreeF, Int, Tree](heightAlgebra, projectTree)
  val product: List => Int = cata[ListF, Int, List](productAlgebra, projectList)
  val productFix: FixedList => Int = cataFix(productAlgebra)
  val heightFix: FixedTree => Int = cataFix(heightAlgebra)
  val mkString: List => String = cata(mksStringAlgebra, projectList)
  val mkStringFix: FixedList => String = cataFix(mksStringAlgebra)

  println(height(intTree))
  println(product(ints))
  println(productFix(fixedInts))
  println(heightFix(fixedIntTree))

  def charCodes(from: String): List = {
    if(from.nonEmpty)
      Cons(from.head.toInt, charCodes(from.tail))
    else Nil
  }

  println(mkString(charCodes("cata")))

  val charCoAlgebra: String => ListF[String] =
    state => {
      if(state.nonEmpty) Some((state.head.toInt, state.tail))
      else               None
    }

  val embed: ListF[List] => List = {
    case Some((head, tail)) => Cons(head, tail)
    case None               => Nil
  }

  def ana[F[_]: Functor, A, B](coAlgebra: A => F[A], embed: F[B] => B): A => B = {
    def loop(state: A): B = embed(map(coAlgebra(state), loop))
    loop
  }

  val rangeCoAlgebra: Int => ListF[Int] = i => {
    if(i > 0) Some((i, i - 1))
    else      None
  }

  val range: Int => List = ana(rangeCoAlgebra, embed)

  println(mkString(ana(charCoAlgebra, embed).apply("cata")))
  println(mkString(range(3)))

  val factorial: Int => Int = range andThen product

  println(factorial(3))

  val showRange: Int => String =
    range andThen mkString

  println(showRange(3))
}
