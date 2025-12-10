package com.example.buoi8.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buoi8.model.MovieResponse
import com.example.buoi8.repository.APIMovieRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel(private val movieRepository: APIMovieRepository) : ViewModel() {
    val responseValue: MutableLiveData<Response<MovieResponse>> = MutableLiveData()

    fun getPopularMovies(apiKey: String, language: String = "en-US", page: Int = 1) {
        viewModelScope.launch {
            val response = movieRepository.getPopularMovies(apiKey, language, page)
            responseValue.value = response
        }
    }
}

