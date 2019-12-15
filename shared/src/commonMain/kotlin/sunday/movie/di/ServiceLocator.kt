package sunday.movie.di

import io.ktor.client.HttpClient
import io.ktor.client.features.UserAgent
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.JSON

object ServiceLocator {

    val getHttpClient by lazy { makeHttpClient() }

    private fun makeHttpClient(): HttpClient {
        return HttpClient {
            expectSuccess = false
            install(JsonFeature) {
                serializer = KotlinxSerializer(json = JSON.nonstrict).apply {
                    //                    setMapper(SearchResultResponse::class, SearchResultResponse.serializer())
                }
            }
//            install(MovieBrowserApiAdapter)
            install(UserAgent) {
                agent = "kotlinlibs"
            }
        }
    }
}