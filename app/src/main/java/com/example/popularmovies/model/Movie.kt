package com.example.popularmovies.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
    @SerializedName("title") var title: String,
    @SerializedName("release_date") var releaseDate: String,
    @SerializedName("vote_average") var rating: Double,
    @SerializedName("overview") var plot: String,
    @SerializedName("poster_path") var poster: String,
    @SerializedName("backdrop_path") var backDrop: String
): Parcelable