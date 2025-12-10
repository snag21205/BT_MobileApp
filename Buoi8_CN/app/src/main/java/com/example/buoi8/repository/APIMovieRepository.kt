package com.example.buoi8.repository

import com.example.buoi8.model.MovieResponse
import com.example.buoi8.model.RetrofitInstance
import retrofit2.Response

class APIMovieRepository {
    suspend fun getPopularMovies(apiKey: String, language: String = "en-US", page: Int = 1): Response<MovieResponse> {
        return RetrofitInstance.apiTMDb.getPopularMovies(apiKey, language, page)
    }
}

