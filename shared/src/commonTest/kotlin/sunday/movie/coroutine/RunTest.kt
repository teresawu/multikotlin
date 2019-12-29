package sunday.movie.coroutine

internal expect fun <T> runTest(block: suspend () -> T): T