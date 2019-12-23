package sunday.movie.data.api

import io.mockk.coEvery
import io.mockk.mockk
import sunday.movie.data.model.PopularMovieApiDTO
import kotlin.test.Test

class MovieApiTest {

    private val api = mockk<MovieApi>()

    @Test
    fun `returns PopularMovieApiDTO from api`() {
        val movieApiDTO = mockk<PopularMovieApiDTO>()
        coEvery {
            api.getMovies()
        } returns movieApiDTO
    }
}