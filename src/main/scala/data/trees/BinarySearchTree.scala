package data.trees

case class BinarySearchTree[T](
                                left: Option[BinarySearchTree[T]],
                                key: T,
                                right: Option[BinarySearchTree[T]],
                                parent: Option[BinarySearchTree[T]])(implicit ord: Ordering[T]){

  def inOrder(): List[T] = {
    val l = left.map(_.inOrder()).getOrElse(List())
    val r = right.map(_.inOrder()).getOrElse(List())
    l ++ (key::r)
  }
  def insert(newKey: T): BinarySearchTree[T] = {
    if(ord.lteq(newKey, key)){
      left match {
        case Some(l) => copy(left = Some(l.insert(newKey)))
        case None =>
          copy(left = Some(BinarySearchTree[T](None, newKey, None, None)))
      }
    }else{
      right match {
        case Some(r) => copy(right = Some(r.insert(newKey)))
        case None =>
          copy(right = Some(BinarySearchTree[T](None, newKey, None, None)))
      }
    }
  }

  def delete(k: T):Option[BinarySearchTree[T]] = ??? //TODO Consider 4 cases. Introduction to Algorithms, 3rd, p297

  def find(k: T): Option[BinarySearchTree[T]] = {
    if(ord.equiv(k, key))
      Some(this)
    else if(ord.lt(k, key)){
      left.flatMap(_.find(k))
    }else{
      right.flatMap(_.find(k))
    }
  }

  def min: BinarySearchTree[T] = left match {
    case Some(l) => l.min
    case None => this
  }

  def max: BinarySearchTree[T] = right match {
    case Some(r) => r.max
    case None => this
  }
}

object BinarySearchTree extends App {
  val bst = createRoot(4)
  val newBST = bst.insert(2).insert(5).insert(1)

  println(bst)
  println(bst.inOrder())
  println(newBST)
  println(newBST.inOrder())
  println(newBST.find(0))
  println(newBST.find(2))
  println(newBST.find(5))
  println(newBST.min)
  println(newBST.max)

  def createRoot[T: Ordering](key: T): BinarySearchTree[T] = BinarySearchTree[T](None, key, None, None)
}