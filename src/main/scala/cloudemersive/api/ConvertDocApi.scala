package cloudemersive.api

import com.cloudmersive.client.ConvertDocumentApi

import java.io.File
import scala.util.Try

object ConvertDocApi {
  lazy val apiInstance = new ConvertDocumentApi

  def convertDocument(path: String, fn: ConvertDocumentApi => File => Array[Byte]): Try[Array[Byte]] = {
    val inputFile = new File(path) // File | Input file to perform the operation on.
    Try{
      fn(apiInstance)(inputFile)
    }
  }
}
