package sunday.movie.domain.model

import com.soywiz.klock.DateTime

private const val MOVIE_POSTER_BASE_URL = "https://image.tmdb.org/t/p/original"

data class Movie(
    val popularity: Double,
    val id: Int,
    val video: Boolean,
    val voteCount: Int,
    val voteAverage: Double,
    val title: String,
    val releaseDate: DateTime,
    val originalLanguage: String,
    val originalTitle: String,
    val genreIds: List<Int>,
    val backdropPath: String,
    val adult: Boolean,
    val overview: String,
    val posterPath: String
) {
    val completePosterPath = MOVIE_POSTER_BASE_URL + posterPath
}