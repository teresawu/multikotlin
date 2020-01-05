package sunday.movie.domain.usecase

import sunday.movie.data.model.ApiError
import sunday.movie.data.model.Either
import sunday.movie.domain.model.Movie
import sunday.movie.domain.repo.MovieRepository

class GetMovieUseCaseImpl(private val movieRepository: MovieRepository) : GetMovieUseCase {
    override suspend fun invoke(): Either<ApiError, Movie> {
        return movieRepository.getMovieModel()
    }
}