package sunday.movie.ui

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.coroutines.Dispatchers
import sunday.movie.R
import sunday.movie.di.ServiceLocator

class MovieActivity : Activity() {
    private val movieViewModel by lazy {
        MovieViewModel(ServiceLocator.movieUserCase, Dispatchers.Default)
    }
    private val moviesAdapter by lazy { MovieAdapter() }
    private var movieState = MovieState()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        movieList.apply {
            layoutManager = GridLayoutManager(this@MovieActivity, 2)
            adapter = moviesAdapter
        }
        movieState = movieViewModel.loadMovie()

    }
}