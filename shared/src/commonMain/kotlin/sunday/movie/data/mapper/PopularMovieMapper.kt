package sunday.movie.data.mapper

import sunday.movie.data.model.PopularMovieApiDTO
import sunday.movie.domain.model.PopularMovie

class PopularMovieMapper : Mapper<PopularMovieApiDTO, PopularMovie> {
    override fun map(popularMovieApiDTO: PopularMovieApiDTO): PopularMovie {
        return PopularMovie(popularMovieApiDTO.totalPages, popularMovieApiDTO.results)
    }
}