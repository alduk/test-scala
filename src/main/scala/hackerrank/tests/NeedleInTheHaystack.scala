package hackerrank.tests

import scala.collection.mutable.ArrayBuffer

object NeedleInTheHaystack extends App{

  // create lookup table for pattern
  def createLookupTable(pattern: String): ArrayBuffer[Int] = {
    val table = ArrayBuffer.fill(pattern.length)(-1)
    table(0) = 0
    var length = 0
    var i = 1
    while(i < pattern.length){
      if(pattern(i) == pattern(length)) {
        length += 1
        table(i) = length
        i += 1
      } else {
        if(length == 0) {
          table(i) = 0
          i += 1
        } else {
          length = table(length - 1)
        }
      }
    }
    table
  }

  def findNeedle(needleSize: Int, needle: String, haystack: String): ArrayBuffer[Int] = {
    // Base cases
    if(needle.length > haystack.length){
      ArrayBuffer(-1)
    }else if(needle == haystack){
      ArrayBuffer(0)
    }
    else {
      val ret = ArrayBuffer.empty[Int]
      val table = createLookupTable(needle)
      println(table)

      var haystackP = 0
      var needleP = 0

      while(haystackP < haystack.length) {
        if(haystack(haystackP) == needle(needleP)){
          haystackP += 1
          needleP += 1
        }
        if (needleP == needle.length) {
          ret.append(haystackP - needleP)
          needleP = table(needleP -1)
        } else{
          if(haystackP < haystack.length && haystack(haystackP) != needle(needleP)){
            if( needleP != 0)
              needleP = table(needleP - 1)
            else
              haystackP +=1
          }
        }
      }

      ret
    }
  }

  val lines = List(
    "2", "na", "banananobano",
    "6", "foobar", "foo",
    "9", "foobarfoo", "barfoobarfoobarfoobarfoobarfoo")

  lines.grouped(3).foreach {g =>
    println(g)
    findNeedle(g(0).toInt, g(1), g(2)).foreach(println)
    println
  }

}
