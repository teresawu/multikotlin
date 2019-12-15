package sunday.movie.data.mapper

import io.mockk.every
import io.mockk.mockk
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import sunday.movie.data.model.MovieApiDTO
import kotlin.test.Test
import kotlin.test.assertEquals

@UnstableDefault
class MovieMapperTest {
    private val movieApiDTO = mockk<MovieApiDTO>()
    private val sut = MovieMapper()
    private val TITLE = "Avengers: Endgame"
    private val PATH = "/or06FN3Dka5tukK1e9sl16pB3iy.jpg"

    @Test
    fun `map should return empty title when network title is empty`() {
        val result = sut.map(movieApiDTO)
        assertEquals(result.title, "")
    }


    @Test
    fun `map should return title when network title is not empty`() {
        every { movieApiDTO.title } answers { TITLE }
        val result = sut.map(movieApiDTO)
        assertEquals(result.title, TITLE)
    }

    @Test
    fun `map should return empty path when network path is empty`() {
        val result = sut.map(movieApiDTO)
        assertEquals(result.posterPath, "")
    }

    @Test
    fun `map should return path when network path is not empty`() {
        every { movieApiDTO.posterPath } answers { PATH }
        val result = sut.map(movieApiDTO)
        assertEquals(result.posterPath, PATH)
    }

    @Test
    fun `parse movie json data to MovieApiDTO`() {
        val movieJson = Json.parse(MovieApiDTO.serializer(), JsonData().movieJson)
        assertEquals("Avengers: Endgame", movieJson.title)
        assertEquals(299534, movieJson.id)
    }
}