package sunday.movie.data.mapper

import sunday.movie.data.model.MovieApiDTO
import sunday.movie.domain.model.Item
import sunday.movie.domain.model.Movie

class MovieMapper : Mapper<MovieApiDTO, Movie> {
    private val MOVIE_POSTER_BASE_URL = "https://image.tmdb.org/t/p/original"
    override fun map(input: MovieApiDTO): Movie {
        val movieList = ArrayList<Item>()
        input.results.forEach { input ->
            movieList.add(
                Item(
                    input.id,
                    input.title.orEmpty(),
                    input.popularity,
                    input.posterPath.orEmpty(),
                    MOVIE_POSTER_BASE_URL + input.posterPath
                )
            )
        }
        return Movie(input.totalPages, movieList)
    }
}