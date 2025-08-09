package com.example.movieapp.data.local


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.model.MovieDetail

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val imdbId: String,
    val title: String,
    val year: String,
    val poster: String,
    val searchQuery: String
) {
    fun toDomain(): Movie {
        return Movie(imdbId, title, year, poster)
    }
}

@Entity(tableName = "movie_details")
data class MovieDetailEntity(
    @PrimaryKey val imdbId: String,
    val title: String,
    val year: String,
    val runtime: String,
    val genre: String,
    val director: String,
    val actors: String,
    val plot: String,
    val poster: String,
    val rating: String
) {
    fun toDomain(): MovieDetail {
        return MovieDetail(imdbId, title, year, runtime, genre, director, actors, plot, poster, rating)
    }
}

