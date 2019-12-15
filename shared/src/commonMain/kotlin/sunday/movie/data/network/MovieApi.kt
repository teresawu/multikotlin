package sunday.movie.data.network

import sunday.movie.data.data.PopularMovieApiDTO

interface MovieApi {
    suspend fun getMovies(): PopularMovieApiDTO
}