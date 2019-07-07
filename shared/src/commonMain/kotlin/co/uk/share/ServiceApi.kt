package co.uk.share

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.request.get
import io.ktor.client.request.url

class ServiceApi(val engine: HttpClientEngine) {
    private val httpClient = HttpClient(engine)

    suspend fun fetchData(): String {
        return httpClient.get {
            url("https://newsapi.org/v2/everything?q=movies&apiKey=079dac74a5f94ebdb990ecf61c8854b7")
        }
    }
}