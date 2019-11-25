package com.example.popularmovies.main

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.popularmovies.R
import com.example.popularmovies.data.MoviesApi
import com.example.popularmovies.model.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

@Suppress("DEPRECATION")
class MovieAdapter(private val movies: List<Movie>, private val onClick: (Movie) -> Unit) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val baseUrl = MoviesApi.imageBaseUrl
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener { onClick(movies[adapterPosition]) }
        }

        @SuppressLint("SetTextI18n")
        fun bind(movie: Movie) {
            itemView.tvNumber.text = "${(adapterPosition + 1)}."
            Glide.with(context).load(baseUrl + movie.poster).into(itemView.ivPoster)
        }
    }
}