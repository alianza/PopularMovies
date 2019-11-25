package com.example.popularmovies.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.popularmovies.R
import com.example.popularmovies.detail.DetailActivity
import com.example.popularmovies.model.Movie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private val movieList = arrayListOf<Movie>()
    private val movieAdapter = MovieAdapter(movieList) {movie -> onMovieClick(movie) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
        initViews()
        initViewModel()
    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter
        rvMovies.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        rvMovies.adapter = movieAdapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        // Observe reminders from the view model, update the list when the data is changed.
        viewModel.movies.observe(this, Observer { movies ->
            this@MainActivity.movieList.clear()
            this@MainActivity.movieList.addAll(movies)
            movieAdapter.notifyDataSetChanged()
        })
    }

    private fun setListeners() {
        btnSubmit.setOnClickListener { onSubmitClick() }
    }

    private fun onMovieClick(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("Movie", movie)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    fun hideKeyboard() {
        val imm = applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(window.decorView.rootView.windowToken, 0)
    }

    private fun onSubmitClick() {
        if (!etYear.text.isNullOrEmpty()) {
            viewModel.getMoviesByReleaseYear(etYear.text.toString().toInt())
            hideKeyboard()
        }
    }
}
