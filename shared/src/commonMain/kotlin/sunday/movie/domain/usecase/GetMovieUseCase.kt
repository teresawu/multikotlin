package sunday.movie.domain.usecase

import sunday.movie.data.model.ApiError
import sunday.movie.data.model.Either
import sunday.movie.domain.model.Movie

interface GetMovieUseCase {

    suspend operator fun invoke(): Either<ApiError, Movie>

}