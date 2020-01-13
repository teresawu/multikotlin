package sunday.movie.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import sunday.movie.data.model.fold
import sunday.movie.domain.usecase.GetMovieUseCase
import kotlin.coroutines.CoroutineContext

class MovieViewModel(
    private val getMovieUseCaseImpl: GetMovieUseCase,
    private val defaultContext: CoroutineContext
) {
    protected val scope by lazy {
        PresenterCoroutineScope(
            defaultContext
        )
    }

    private val movieState = MovieState()

    fun loadMovie(): MovieState {
        scope.launch {
            getMovieUseCaseImpl.invoke().fold(
                { left ->
                    {
                        movieState.loading = true
                        movieState.success = false
                    }
                },
                { right ->
                    {
                        movieState.loading = false
                        movieState.success = true
                        movieState.itemList = right.results
                    }
                })
        }
        return movieState
    }
}

class PresenterCoroutineScope(
    context: CoroutineContext
) : CoroutineScope {
    private var onViewDetachJob = Job()
    override val coroutineContext: CoroutineContext = context + onViewDetachJob
    fun viewDetached() {
        onViewDetachJob.cancel()
    }
}