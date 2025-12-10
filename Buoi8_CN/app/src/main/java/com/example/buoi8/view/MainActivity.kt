package com.example.buoi8.view

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.buoi8.CmmVariable
import com.example.buoi8.R
import com.example.buoi8.adapter.MovieAdapter
import com.example.buoi8.repository.APIMovieRepository
import com.example.buoi8.viewmodel.MovieViewModel
import com.example.buoi8.viewmodel.MovieViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val listView = findViewById<ListView>(R.id.listView)

        // Initialize ViewModel
        val viewModelFactory = MovieViewModelFactory(APIMovieRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)

        // Fetch popular movies
        viewModel.getPopularMovies(CmmVariable.API_KEY, "en-US", 1)

        // Observe response
        viewModel.responseValue.observe(this) { response ->
            if (response.isSuccessful) {
                val movieResponse = response.body()
                movieResponse?.let {
                    val movies = it.results
                    Log.d("MainActivity", "Total movies fetched: ${movies.size}")

                    // Use custom adapter for better display
                    val adapter = MovieAdapter(this, movies)
                    listView.adapter = adapter

                    Toast.makeText(
                        this,
                        "Loaded ${movies.size} popular movies",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Log.e("MainActivity", "Error: ${response.code()} - ${response.message()}")
                Toast.makeText(
                    this,
                    "Error loading movies: ${response.message()}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }
}