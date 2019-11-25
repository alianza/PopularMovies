package com.example.popularmovies.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.popularmovies.data.MoviesRepository
import com.example.popularmovies.model.Movie
import com.example.popularmovies.model.MoviesResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val moviesRepository = MoviesRepository()
    val movies = MutableLiveData<Array<Movie>>()
    val error = MutableLiveData<String>()

    fun getMoviesByReleaseYear(releaseYear: Int) {
        moviesRepository.getMoviesByReleaseYear(releaseYear).enqueue(object : Callback<MoviesResult> {
            override fun onResponse(call: Call<MoviesResult>, response: Response<MoviesResult>) {
                if (response.isSuccessful) {
                    movies.value = response.body()?.movies
                    println(response.body())
                }
                else error.value = "An error occurred: ${response.errorBody().toString()}"
            }

            override fun onFailure(call: Call<MoviesResult>, t: Throwable) {
                error.value = t.message
                println("Gefaald! ${t.message}")
            }
        })
    }
}