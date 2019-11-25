package com.example.popularmovies.data

import com.example.popularmovies.model.MoviesResult
import retrofit2.Call

class MoviesRepository {
    private val moviesApi: MoviesApiService = MoviesApi.createApi()

    fun getMoviesByReleaseYear(releaseYear: Int): Call<MoviesResult> = moviesApi.getMoviesByReleaseYear(releaseYear)
}
