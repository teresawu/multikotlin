package sunday.movie.data.repo

import io.mockk.mockk
import org.junit.Test
import sunday.movie.data.api.MovieApi
import sunday.movie.data.mapper.MovieMapper
import sunday.movie.data.model.fold
import kotlin.test.assertEquals
import kotlin.test.fail

class MovieRepositoryImplTest {
    private val movieApi = mockk<MovieApi>()
    private val movieMapper = mockk<MovieMapper>()
    private val sut = MovieRepositoryImpl(movieApi, movieMapper)
    private val MOVIE_POSTER_BASE_URL = "https://image.tmdb.org/t/p/original"

    @Test
    fun getMovieModelFromRepository() {
        suspend {
            sut.getMovieModel().fold(
                { left -> fail("Should return right but was left") },
                { right -> assertEquals(MOVIE_POSTER_BASE_URL+right.posterPath, right.completePosterPath) })
        }
    }
}