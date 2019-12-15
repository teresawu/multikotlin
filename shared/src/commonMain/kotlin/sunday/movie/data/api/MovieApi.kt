package sunday.movie.data.api

import sunday.movie.data.model.PopularMovieApiDTO

interface MovieApi {
    suspend fun getMovies(): PopularMovieApiDTO
}