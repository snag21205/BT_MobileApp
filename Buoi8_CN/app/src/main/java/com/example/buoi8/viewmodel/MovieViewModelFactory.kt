package com.example.buoi8.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.buoi8.repository.APIMovieRepository

class MovieViewModelFactory(private val movieRepository: APIMovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(movieRepository) as T
    }
}

