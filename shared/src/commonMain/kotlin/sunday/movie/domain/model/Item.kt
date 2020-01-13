package sunday.movie.domain.model

data class Item(
    val id: Int,
    val title: String,
    val popularity: Double,
    val posterPath: String,
    val completePosterPath:String
)