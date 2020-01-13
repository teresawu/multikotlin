package sunday.movie.di

import io.ktor.client.engine.HttpClientEngine
import sunday.movie.data.api.MovieApiImpl
import sunday.movie.data.mapper.MovieMapper
import sunday.movie.data.repo.MovieRepositoryImpl
import sunday.movie.domain.repo.MovieRepository
import sunday.movie.domain.usecase.GetMovieUseCase
import sunday.movie.domain.usecase.GetMovieUseCaseImpl

object ServiceLocator {

    val moviesApi by lazy { MovieApiImpl(PlatformServiceLocator.httpClientEngine) }

    val getMovies: MovieRepository
        get() = MovieRepositoryImpl(moviesApi, MovieMapper())

    val movieUserCase: GetMovieUseCase
        get() = GetMovieUseCaseImpl(getMovies)
}

expect object PlatformServiceLocator {
    val httpClientEngine: HttpClientEngine
}
