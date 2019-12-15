package sunday.movie.domain.model

private const val MOVIE_POSTER_BASE_URL = "https://image.tmdb.org/t/p/original"

data class Movie(
    val id: Int,
    val title: String,
    val popularity: Double,
    val posterPath: String
) {
    val completePosterPath = MOVIE_POSTER_BASE_URL + posterPath
}