package sunday.movie.data.mapper

import io.mockk.every
import io.mockk.mockk
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import org.junit.Before
import sunday.movie.data.model.ItemApiDTO
import sunday.movie.data.model.MovieApiDTO
import sunday.movie.domain.model.Movie
import kotlin.test.Test
import kotlin.test.assertEquals

@UnstableDefault
class MovieMapperTest {
    private val popularMovieApiDTO = mockk<MovieApiDTO>()
    private val sut = MovieMapper()
    private lateinit var movie: Movie

    @Before
    fun setUp() {
        every { popularMovieApiDTO.totalPages } returns 10
        every { popularMovieApiDTO.results } returns listOf()
        movie = sut.map(popularMovieApiDTO)
    }

    @Test
    fun returnTotalPage() {
        assertEquals(movie.totalPages, 10)
    }


    @Test
    fun returnEmptyListWhenListIsNotSet() {
        assertEquals(movie.results.size, 0)
    }

    @Test
    fun returnMovieListWhenListIsSet() {
        val movieJson = Json.parse(ItemApiDTO.serializer(), JsonData().movieJson)
        every { popularMovieApiDTO.results } returns listOf(movieJson)
        val result = sut.map(popularMovieApiDTO)
        assertEquals(result.results.size, 1)
        assertEquals(result.results[0].id, 299534)
    }
}