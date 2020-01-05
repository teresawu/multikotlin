package sunday.movie.domain.usecase

import io.mockk.mockk
import org.junit.Test
import sunday.movie.data.model.fold
import sunday.movie.domain.model.Movie
import sunday.movie.domain.repo.MovieRepository
import kotlin.test.fail

class GetMovieUseCaseImplTest {
    private val movieRepository = mockk<MovieRepository>()
    private val sut = GetMovieUseCaseImpl(movieRepository)

    @Test
    fun invokeReturnsMovieModel() {
        suspend {
            sut.invoke().fold(
                { left -> fail("Should return right but was left") },
                { right -> assert(right is Movie) })
        }
    }
}