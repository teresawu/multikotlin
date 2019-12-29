package sunday.movie.data.mapper

import io.mockk.every
import io.mockk.mockk
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import org.junit.Before
import sunday.movie.data.model.MovieApiDTO
import sunday.movie.domain.model.Movie
import kotlin.test.Test
import kotlin.test.assertEquals

@UnstableDefault
class MovieMapperTest {
    private val movieApiDTO = mockk<MovieApiDTO>()
    private val sut = MovieMapper()
    private val TITLE = "Avengers: Endgame"
    private val PATH = "/or06FN3Dka5tukK1e9sl16pB3iy.jpg"
    private lateinit var movie: Movie

    @Before
    fun setUp() {
        every { movieApiDTO.id } returns 1
        every { movieApiDTO.title } returns ""
        every { movieApiDTO.popularity } returns 1.0
        every { movieApiDTO.posterPath } returns ""
        movie = sut.map(movieApiDTO)
    }

    @Test
    fun returnEmptyTitleWhenNetworkTitleIsEmpty() {
        assertEquals(movie.title, "")
    }


    @Test
    fun returnTitleWhenNetworkTitleIsNotEmpty() {
        every { movieApiDTO.title } returns TITLE
        val result = sut.map(movieApiDTO)
        assertEquals(result.title, TITLE)
    }


    @Test
    fun returnEmptyPathWhenNetworkPathIsEmpty() {
        assertEquals(movie.posterPath, "")
    }

    @Test
    fun returnPathWhenNetworkPathIsNotEmpty() {
        every { movieApiDTO.posterPath } returns PATH
        val result = sut.map(movieApiDTO)
        assertEquals(result.posterPath, PATH)
    }

    @Test
    fun parseMovieJsonToMovieApiDTO() {
        val movieJson = Json.parse(MovieApiDTO.serializer(), JsonData().movieJson)
        assertEquals("Avengers: Endgame", movieJson.title)
        assertEquals(299534, movieJson.id)
    }
}