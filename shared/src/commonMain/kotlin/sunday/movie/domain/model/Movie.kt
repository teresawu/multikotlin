package sunday.movie.domain.model

data class Movie(
    val totalPages: Int,
    val results: List<Item>
)