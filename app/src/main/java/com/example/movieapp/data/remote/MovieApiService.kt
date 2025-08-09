package com.example.movieapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("/")
    suspend fun searchMovies(
        @Query("s") query: String,
        @Query("apikey") apiKey: String = "a0783fa9"
    ): SearchResponseDto

    @GET("/")
    suspend fun getMovieDetail(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String = "a0783fa9"
    ): MovieDetailDto
}
