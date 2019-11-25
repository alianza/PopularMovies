package com.example.popularmovies.model

import com.google.gson.annotations.SerializedName

@Suppress("ArrayInDataClass")
data class MoviesResult (
    @SerializedName("results") var movies: Array<Movie>
)