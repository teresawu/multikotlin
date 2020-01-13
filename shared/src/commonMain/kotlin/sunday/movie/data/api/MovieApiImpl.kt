package sunday.movie.data.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
import sunday.movie.data.model.ApiError
import sunday.movie.data.model.Auth
import sunday.movie.data.model.Either
import sunday.movie.data.model.MovieApiDTO

private const val BASE_URL = "api.themoviedb.org/4"
private const val HEADER_AUTHORIZATION = "Authorization"
private const val ENCODED_PATH = "/discover/movie"
private const val BEARER = "Bearer "
private const val SORT_BY = "sort_by"
private const val DESC = "popularity.desc"

@Suppress("EXPERIMENTAL_API_USAGE")
class MovieApiImpl(clientEngine: HttpClientEngine) : MovieApi {

    private val client by lazy {
        HttpClient(clientEngine) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(Json.nonstrict)
            }
        }
    }

    override suspend fun getMovies(): Either<ApiError, MovieApiDTO> {
        val response = client.get<HttpResponse> {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                encodedPath = ENCODED_PATH
                parameter(SORT_BY, DESC)
                header(HEADER_AUTHORIZATION, BEARER + Auth.TOKEN)
            }
        }

        val jsonBody = response.readText()
        val result = Json.parse(MovieApiDTO.serializer(), jsonBody)
        return Either.Right(result)
    }
}