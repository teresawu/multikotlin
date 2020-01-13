package sunday.movie.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieApiDTO(
    val page: Int,
    @SerialName("total_results") val totalResults: Int,
    @SerialName("total_pages") val totalPages: Int,
    val results: List<ItemApiDTO>
)

@Serializable
data class ItemApiDTO(
    val popularity: Double,
    val id: Int,
    val video: Boolean,
    @SerialName("vote_count") val voteCount: Int,
    @SerialName("vote_average") val voteAverage: Double,
    val title: String,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("original_language") val originalLanguage: String,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("genre_ids") val genreIds: List<Int>,
    @SerialName("backdrop_path") val backdropPath: String,
    val adult: Boolean,
    val overview: String,
    @SerialName("poster_path") val posterPath: String
)