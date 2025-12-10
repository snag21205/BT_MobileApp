package com.example.buoi8.model

import com.example.buoi8.CmmVariable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class APITMDb {

    // Interface nội bộ cho Retrofit
    private interface TMDbService {
        @GET("movie/popular")
        suspend fun getPopularMovies(
            @Query("api_key") apiKey: String,
            @Query("language") language: String,
            @Query("page") page: Int
        ): Response<MovieResponse>
    }

    // Retrofit instance nội bộ
    private val retrofit = Retrofit.Builder()
        .baseUrl(CmmVariable.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(TMDbService::class.java)

    // Function public để gọi API
    suspend fun getPopularMovies(
        apiKey: String,
        language: String = "en-US",
        page: Int = 1
    ): Response<MovieResponse> {
        return withContext(Dispatchers.IO) {
            service.getPopularMovies(apiKey, language, page)
        }
    }
}

