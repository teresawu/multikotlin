package sunday.movie.domain.model

import sunday.movie.data.model.MovieApiDTO

data class PopularMovie(
    val totalPages: Int,
    val results: List<MovieApiDTO>
)