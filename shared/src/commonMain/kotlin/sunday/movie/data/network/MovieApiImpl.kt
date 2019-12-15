package sunday.movie.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
import sunday.movie.data.data.Auth
import sunday.movie.data.data.PopularMovieApiDTO


private const val BASE_URL = "api.themoviedb.org/4"
private const val HEADER_AUTHORIZATION = "Authorization"

class MovieApiImpl(val httpClient: HttpClient) : MovieApi {

    override suspend fun getMovies(): PopularMovieApiDTO {
        val response = httpClient.get<HttpResponse> {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                encodedPath = "/discover/movie"
                parameter("sort_by", "popularity.desc")
                header(HEADER_AUTHORIZATION, Auth.TOKEN.asBearerToken())
            }
        }

        val jsonBody = response.readText()
        return Json.parse(PopularMovieApiDTO.serializer(), jsonBody)
    }

    private fun String.asBearerToken() = "Bearer $this"
}