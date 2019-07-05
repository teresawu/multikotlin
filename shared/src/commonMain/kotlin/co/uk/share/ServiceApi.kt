import co.uk.share.NewsList
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url

class ServiceApi() {
    private val httpClient = HttpClient()
    suspend fun fetchData(): NewsList {
        return httpClient.get<NewsList> {
            url("https://newsapi.org/v2/everything?q=movies&apiKey=079dac74a5f94ebdb990ecf61c8854b7")
        }
    }
}