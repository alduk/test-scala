package hackerrank.tests

class SegmentTree(val st: Array[Int], val n: Int) {
  // A recursive function that constructs Segment Tree for array[ss..se].
  // si is index of current node in segment tree st
  def constructSTUtil(arr: Array[Int], ss: Int, se: Int, si: Int): Int = {
    // If there is one element in array, store it in current node of
    // segment tree and return
    if (ss == se) {
      st(si) = arr(ss)
    }else {
      // If there are more than one elements, then recur for left and
      // right subtrees and store the sum of values in this node
      val mid = getMid(ss, se)
      st(si) = constructSTUtil(arr, ss, mid, si * 2 + 1) + constructSTUtil(arr, mid + 1, se, si * 2 + 2)
    }
    st(si)
  }
  // A utility function to get the middle index from corner indexes.
  def getMid(s: Int, e: Int): Int = {
     s + (e - s) / 2;
  }

  // Return sum of elements in range from index qs (quey start) to
  // qe (query end).  It mainly uses getSumUtil()
  def getSum(qs: Int, qe: Int): Int =
  {
    // Check for erroneous input values
    if (qs < 0 || qe > n - 1 || qs > qe) {
      System.out.println("Invalid Input");
      return -1;
    }
    getSumUtil(0, n - 1, qs, qe, 0);
  }

  /*  A recursive function to get the sum of values in given range
    of the array.  The following are parameters for this function.

  st    --> Pointer to segment tree
  si    --> Index of current node in the segment tree. Initially
            0 is passed as root is always at index 0
  ss & se  --> Starting and ending indexes of the segment represented
                by current node, i.e., st[si]
  qs & qe  --> Starting and ending indexes of query range */
  def getSumUtil(ss: Int, se: Int, qs: Int, qe: Int, si: Int): Int =
  {
    // If segment of this node is a part of given range, then return
    // the sum of the segment
    if (qs <= ss && qe >= se) {
      st(si)
    }else if (se < qs || ss > qe) {
      // If segment of this node is outside the given range
      0
    }else {
      // If a part of this segment overlaps with the given range
      val mid = getMid(ss, se);
      getSumUtil(ss, mid, qs, qe, 2 * si + 1) + getSumUtil(mid + 1, se, qs, qe, 2 * si + 2);
    }
  }

  // The function to update a value in input array and segment tree.
  // It uses updateValueUtil() to update the value in segment tree
  def updateValue(arr: Array[Int], n: Int, i: Int, new_val: Int): Unit = { // Check for erroneous input index
    if (i < 0 || i > n - 1) {
      System.out.println("Invalid Input")
      return
    }
    // Get the difference between new value and old value
    val diff = new_val - arr(i)
    // Update the value in array
    arr(i) = new_val
    // Update the values of nodes in segment tree
    updateValueUtil(0, n - 1, i, diff, 0)
  }

  /* A recursive function to update the nodes which have the given
   index in their range. The following are parameters
    st, si, ss and se are same as getSumUtil()
    i    --> index of the element to be updated. This index is in
             input array.
   diff --> Value to be added to all nodes which have i in range */
  def updateValueUtil(ss: Int, se: Int, i: Int,diff: Int, si: Int): Unit =
  {
    // Base Case: If the input index lies outside the range of
    // this segment
    if (i < ss || i > se) {

    }else {

      // If the input index is in range of this node, then update the
      // value of the node and its children
      st(si) = st(si) + diff;
      if (se != ss) {
        val mid = getMid(ss, se);
        updateValueUtil(ss, mid, i, diff, 2 * si + 1);
        updateValueUtil(mid + 1, se, i, diff, 2 * si + 2);
      }
    }
  }

  override def toString: String = s"SegmentTree(${st.mkString("[", ",", "]")}, n=$n)"

}


object SegmentTree{
  def apply(arr: Array[Int]): SegmentTree =  {
    //Height of segment tree
    val height = math.ceil(math.log(arr.length)/math.log(2)) ////// log N
                                                             //////    2
    //Maximum size of segment tree
    val maxSize = 2 * Math.pow(2, height).toInt - 1
    val st = Array.ofDim[Int](maxSize)
    val tree = new SegmentTree(st, arr.length)
    tree.constructSTUtil(arr, 0, arr.length - 1, 0)
    tree
  }
}

object Main extends App {
  val arr = Array(1, 3, 5, 7, 9, 11)
  val segmentTree = SegmentTree(arr)
  println(segmentTree)
  println(segmentTree.getSum(1, 3))
}