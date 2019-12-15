package sunday.movie.data.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
import sunday.movie.data.model.Auth
import sunday.movie.data.model.PopularMovieApiDTO

private const val BASE_URL = "api.themoviedb.org/4"
private const val HEADER_AUTHORIZATION = "Authorization"
private const val ENCODED_PATH = "/discover/movie"
private const val BEARER = "Bearer "
private const val SORT_BY = "sort_by"
private const val DESC = "popularity.desc"

class MovieApiImpl(val httpClient: HttpClient) : MovieApi {

    override suspend fun getMovies(): PopularMovieApiDTO {
        val response = httpClient.get<HttpResponse> {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                encodedPath = ENCODED_PATH
                parameter(SORT_BY, DESC)
                header(HEADER_AUTHORIZATION, BEARER + Auth.TOKEN)
            }
        }

        val jsonBody = response.readText()
        return Json.parse(PopularMovieApiDTO.serializer(), jsonBody)
    }
}