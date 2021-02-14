package cloudemersive.api

import com.cloudmersive.client.model.{LanguageTranslationRequest, LanguageTranslationResponse}
import com.cloudmersive.client.{AnalyticsApi, LanguageTranslationApi}

import scala.util.Try

object NlpApi {
  lazy val analyticsApi = new AnalyticsApi()
  lazy val translationApi = new LanguageTranslationApi()

  def translate(text: String, fn: LanguageTranslationApi => LanguageTranslationRequest => LanguageTranslationResponse): Try[LanguageTranslationResponse] = {
    val req = new LanguageTranslationRequest()
    req.setTextToTranslate(text)
    Try {
      fn(translationApi)(req)
    }
  }
}
