package cloudemersive.api

import com.cloudmersive.client.invoker.ApiClient
import com.cloudmersive.client.invoker.ApiException
import com.cloudmersive.client.invoker.Configuration
import com.cloudmersive.client.invoker.auth._
import com.cloudmersive.client.ConvertDocumentApi

import java.io.{BufferedOutputStream, File, FileOutputStream}
import scala.util.Try


object TestCloudMersive extends App {
  val apiKey = "2c41e5fc-ff17-4b13-8d6b-4f102b610f03"

  val defaultClient: ApiClient = Configuration.getDefaultApiClient
    .setConnectTimeout(50_000)
    .setReadTimeout(50_000)
    .setWriteTimeout(50_000)
  val Apikey: ApiKeyAuth = defaultClient.getAuthentication("Apikey").asInstanceOf[ApiKeyAuth]
  Apikey.setApiKey(apiKey)

  /*ConvertDocApi.convertDocument("/home/alduk/Downloads/Arsen.Kudla.pdf", _.convertDocumentPdfToDocx).map{r =>
    println("Writing result...")
    writeBytes(LazyList.from(r), "/home/alduk/Downloads/Arsen.Kudla.docx")
  }.fold(_.printStackTrace(), _ => println("Finished"))*/

  /*NlpApi.translate("" +
    "Automatically translates input text in English to output text in Russian using advanced Deep Learning and Neural NLP.",
    _.languageTranslationTranslateEngToRus
  ).map(println)
    .fold(_.printStackTrace(), _ => println("Finished"))*/

  convertCV()

  def convertCV(): Unit = {
    ConvertDocApi.convertDocument("/home/alduk/Downloads/Arsen_Kudla_CV.doc", _.convertDocumentDocxToPdf).map { r =>
      println("Writing result...")
      writeBytes(LazyList.from(r), "/home/alduk/Downloads/Arsen_Kudla_CV.pdf")
    }.fold(_.printStackTrace(), _ => println("Finished"))
  }

  private def writeBytes( data : LazyList[Byte], path : String ): Unit = {
    val file = new File(path)
    val target = new BufferedOutputStream( new FileOutputStream(file) )
    try data.foreach( target.write(_) ) finally target.close()
  }
}
