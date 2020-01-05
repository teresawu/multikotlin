package sunday.movie.domain.repo

import sunday.movie.data.model.ApiError
import sunday.movie.data.model.Either
import sunday.movie.domain.model.Movie

interface MovieRepository {
    suspend fun getMovieModel(): Either<ApiError, Movie>
}