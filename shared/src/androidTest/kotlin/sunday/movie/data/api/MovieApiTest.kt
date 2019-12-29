package sunday.movie.data.api

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockHttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.http.fullPath
import kotlinx.coroutines.io.ByteReadChannel
import org.junit.Before
import org.junit.Test
import sunday.movie.coroutine.runTest
import sunday.movie.data.mapper.JsonData
import sunday.movie.data.model.fold
import kotlin.test.assertEquals
import kotlin.test.fail

class MovieApiTest {
    private lateinit var sut: MovieApiImpl

    @Before
    fun setUp() {
        val mockEngine = MockEngine {
            if (this.url.toString().endsWith("/fail")) {
                error("Unhandled ${url.fullPath}")
            } else {
                MockHttpResponse(
                    call = call,
                    status = HttpStatusCode.fromValue(200),
                    content = ByteReadChannel(JsonData().movieJson)
                )
            }
        }

        sut = MovieApiImpl(mockEngine)
    }

    @Test
    fun movieIDShouldMatch() = runTest {
        sut.getMovies().fold(
            { left -> fail("Should return right but was left") },
            { right -> assertEquals(299534, right.id) })
    }
}