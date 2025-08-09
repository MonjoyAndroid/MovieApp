package com.example.movieapp.data.remote

import com.google.gson.annotations.SerializedName

data class SearchResponseDto(
    @SerializedName("Search") val Search: List<MovieDto>,
    @SerializedName("totalResults") val totalResults: String?,
    @SerializedName("Response") val Response: String
)

data class MovieDto(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val imdbId: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Poster") val poster: String
)

data class MovieDetailDto(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Rated") val rated: String,
    @SerializedName("Released") val released: String,
    @SerializedName("Runtime") val runtime: String,
    @SerializedName("Genre") val genre: String,
    @SerializedName("Director") val director: String,
    @SerializedName("Writer") val writer: String,
    @SerializedName("Actors") val actors: String,
    @SerializedName("Plot") val plot: String,
    @SerializedName("Language") val language: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("imdbRating") val imdbRating: String,
    @SerializedName("Response") val response: String
)

