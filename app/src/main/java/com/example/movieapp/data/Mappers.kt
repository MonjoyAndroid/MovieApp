package com.example.movieapp.data

import com.example.movieapp.data.local.MovieDetailEntity
import com.example.movieapp.data.local.MovieEntity
import com.example.movieapp.data.remote.MovieDetailDto
import com.example.movieapp.data.remote.MovieDto
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.model.MovieDetail

// ---- DTO to Entity ----
fun MovieDto.toEntity(searchQuery: String): MovieEntity {
    return MovieEntity(
        imdbId = imdbId,
        title = title,
        year = year,
        poster = poster,
        searchQuery = searchQuery
    )
}

fun MovieDetailDto.toEntity(): MovieDetailEntity {
    return MovieDetailEntity(
        imdbId = imdbRating + title, // temporary unique id
        title = title,
        year = year,
        runtime = runtime,
        genre = genre,
        director = director,
        actors = actors,
        plot = plot,
        poster = poster,
        rating = imdbRating
    )
}

// ---- Entity to Domain ----
fun MovieEntity.toDomain(): Movie {
    return Movie(
        imdbId = imdbId,
        title = title,
        year = year,
        poster = poster
    )
}

fun MovieDetailEntity.toDomain(): MovieDetail {
    return MovieDetail(
        imdbId = imdbId,
        title = title,
        year = year,
        runtime = runtime,
        genre = genre,
        director = director,
        actors = actors,
        plot = plot,
        poster = poster,
        rating = rating
    )
}
