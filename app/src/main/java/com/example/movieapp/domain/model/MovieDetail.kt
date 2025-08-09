package com.example.movieapp.domain.model

data class MovieDetail(
    val imdbId: String,
    val title: String,
    val year: String,
    val runtime: String,
    val genre: String,
    val director: String,
    val actors: String,
    val plot: String,
    val poster: String,
    val rating: String
)

