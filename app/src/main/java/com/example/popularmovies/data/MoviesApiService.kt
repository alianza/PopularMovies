package com.example.popularmovies.data

import com.example.popularmovies.BuildConfig
import com.example.popularmovies.model.MoviesResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {
    @GET("movie?api_key=${BuildConfig.API_KEY}&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    fun getMoviesByReleaseYear(@Query(value = "primary_release_year", encoded = true) releaseYear: Int): Call<MoviesResult>
}