package recursion.schemas

sealed trait Tree

case class Node(
                 left: Tree,
                 value: Int,
                 right: Tree
               ) extends Tree

case object Leaf extends Tree


sealed trait TreeF[+A]

case class NodeF[A](
                     left: A,
                     value: Int,
                     right: A
                   ) extends TreeF[A]

case object LeafF extends TreeF[Nothing]

object TreeF {
  implicit val treeFFunctor = new Functor[TreeF] {
    override def map[A, B](tree: TreeF[A], f: A => B): TreeF[B] = {
      println(s"map:$tree")
      tree match {
        case NodeF(left, i, right) => NodeF(f(left), i, f(right))
        case LeafF => LeafF
      }
    }
  }

  def projectTree: Tree => TreeF[Tree] = {
    case Node(l, v, r) =>
      println(s"projectTree:${Node(l, v, r)}")
      NodeF(l, v, r)
    case Leaf =>
      println(s"projectTree:$Leaf")
      LeafF
  }

  case class Tree2(value: TreeF[Tree2])

  type FixedTree = Fix[TreeF]
}