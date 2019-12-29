package sunday.movie.data.api

import sunday.movie.data.model.ApiError
import sunday.movie.data.model.Either
import sunday.movie.data.model.MovieApiDTO

interface MovieApi {
    suspend fun getMovies(): Either<ApiError, MovieApiDTO>
}