package sunday.movie.data.mapper

import sunday.movie.data.model.MovieApiDTO
import sunday.movie.domain.model.Movie

class MovieMapper : Mapper<MovieApiDTO, Movie> {
    override fun map(input: MovieApiDTO): Movie {
        return Movie(input.id, input.title.orEmpty(), input.popularity, input.posterPath.orEmpty())
    }
}