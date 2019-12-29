package sunday.movie.data.mapper

import io.mockk.every
import io.mockk.mockk
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import org.junit.Before
import sunday.movie.data.model.MovieApiDTO
import sunday.movie.data.model.PopularMovieApiDTO
import sunday.movie.domain.model.PopularMovie
import kotlin.test.Test
import kotlin.test.assertEquals

@UnstableDefault
class PopularMovieMapperTest {
    private val popularMovieApiDTO = mockk<PopularMovieApiDTO>()
    private val sut = PopularMovieMapper()
    private lateinit var popularMovie: PopularMovie

    @Before
    fun setUp() {
        every { popularMovieApiDTO.totalPages } returns 10
        every { popularMovieApiDTO.results } returns listOf()
        popularMovie = sut.map(popularMovieApiDTO)
    }

    @Test
    fun returnTotalPage() {
        assertEquals(popularMovie.totalPages, 10)
    }


    @Test
    fun returnEmptyListWhenListIsNotSet() {
        assertEquals(popularMovie.results.size, 0)
    }

    @Test
    fun returnMovieListWhenListIsSet() {
        val movieJson = Json.parse(MovieApiDTO.serializer(), JsonData().movieJson)
        every { popularMovieApiDTO.results } returns listOf(movieJson)
        val result = sut.map(popularMovieApiDTO)
        assertEquals(result.results.size, 1)
        assertEquals(result.results[0].id, 299534)
    }
}