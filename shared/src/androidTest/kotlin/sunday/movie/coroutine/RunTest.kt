package sunday.movie.coroutine

import kotlinx.coroutines.runBlocking

internal actual fun <T> runTest(block: suspend () -> T): T {
    return runBlocking { block() }
}