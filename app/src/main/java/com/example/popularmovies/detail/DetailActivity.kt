package com.example.popularmovies.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.popularmovies.R
import com.example.popularmovies.data.MoviesApi
import com.example.popularmovies.model.Movie
import kotlinx.android.synthetic.main.activity_detail.*
import java.lang.Exception


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
        initViews()
    }

    private fun initViews() {
        val bundle = intent.extras
        if (bundle != null) {
            val movie: Movie = bundle.get("Movie") as Movie
            Glide.with(this).load(MoviesApi.imageBaseUrl + movie.backDrop).into(ivBackDrop)
            Glide.with(this).load(MoviesApi.imageBaseUrl + movie.poster).into(ivPoster)
            tvTitle.text = movie.title
            tvRating.text = "Rating: ${movie.rating}"
            tvReleaseDate.text = movie.releaseDate
            tvOverview.text = movie.plot
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return try {
            finish()
            true
        } catch (exception: Exception) {
            false
        }
    }
}
