package link.server

import cats.data.Kleisli
import cats.effect.IOApp
import org.http4s.blaze.server.BlazeServerBuilder

import java.awt.Toolkit
import java.awt.datatransfer._
import java.net.{URI, URL}
import scala.util.Try


object LinkServer extends IOApp {
  def writeToClipBoard(s: String, owner: ClipboardOwner = null): Clipboard = {
    val clipboard = Toolkit.getDefaultToolkit.getSystemClipboard
    val transferable = new StringSelection(s)
    clipboard.setContents(transferable, owner)
    clipboard
  }

  def google(search: String) = s"https://www.google.com/search?q=$search"

  def openChromeCmd(uri: String) = s"google-chrome $uri"

  private def validateUrl(searchText: String): Try[URI] = {
    Try(new URL(searchText).toURI)
  }

  val httpProtocol = "http://"

  def browse(searchText: String): String = {
    import scala.sys.process._
    val uri = validateUrl(searchText).recoverWith(_ => validateUrl(s"$httpProtocol$searchText")).fold(
      _ => {
        google(searchText)
      },
      u => {
        if(u.getHost == null || !searchText.contains(".")) google(searchText) else searchText
      }
    )

    openChromeCmd(uri).!!
  }

  import cats.effect._, org.http4s._, org.http4s.dsl.io._, scala.concurrent.ExecutionContext.Implicits.global
  import org.http4s.implicits._

  object ContentQueryParamMatcher extends QueryParamDecoderMatcher[String]("content")


  val openLinkService: Kleisli[IO, Request[IO], Response[IO]] = HttpRoutes.of[IO] {
    case GET -> Root / "open" :? ContentQueryParamMatcher(query) =>
      println(s"Opening $query.")
      val clipboard = writeToClipBoard(query)
      val content = clipboard.getData(DataFlavor.stringFlavor).toString
      browse(content)
      Ok(s"Processed: $content.")

    case GET -> Root / "health" =>
      Ok("OK")

  }.orNotFound

  def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder[IO](global)
      .bindHttp(3333, "0.0.0.0")
      .withHttpApp(openLinkService)
      .serve
      .compile
      .drain
      .as(ExitCode.Success)
}
