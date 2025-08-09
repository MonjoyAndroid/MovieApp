package com.example.movieapp.domain.repository

import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.model.MovieDetail

interface IMovieRepository {
    suspend fun searchMovies(query: String): List<Movie>
    suspend fun getMovieDetail(imdbId: String): MovieDetail
}
