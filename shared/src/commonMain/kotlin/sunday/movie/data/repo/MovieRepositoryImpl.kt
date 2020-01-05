package sunday.movie.data.repo

import sunday.movie.data.api.MovieApi
import sunday.movie.data.mapper.MovieMapper
import sunday.movie.data.model.ApiError
import sunday.movie.data.model.Either
import sunday.movie.data.model.map
import sunday.movie.domain.model.Movie
import sunday.movie.domain.repo.MovieRepository

class MovieRepositoryImpl(
    private val moviesApi: MovieApi,
    private val movieMapper: MovieMapper
) : MovieRepository {
    override suspend fun getMovieModel(): Either<ApiError, Movie> {
        return moviesApi.getMovies().map {
            movieMapper.map(it)
        }
    }
}