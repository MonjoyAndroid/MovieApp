package com.example.movieapp.data.repository

import com.example.movieapp.data.local.MovieDao
import com.example.movieapp.data.remote.MovieApiService
import com.example.movieapp.data.toEntity
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.model.MovieDetail
import com.example.movieapp.domain.repository.IMovieRepository
import com.example.movieapp.presentation.movielist.MovieListViewModel
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: MovieApiService,
    private val dao: MovieDao
) : IMovieRepository {

    override suspend fun searchMovies(query: String): List<Movie> {
        // Default key when blank (fetches a wide variety of results)
        val finalQuery = query.ifBlank { MovieListViewModel.DEFAULT_SEARCH }

        // Check local cache first
        val cached = dao.searchMovies(finalQuery)
        if (cached.isNotEmpty()) {
            return cached.map { it.toDomain() }
        }

        // Call API
        val response = try {
            api.searchMovies(finalQuery)
        } catch (e: Exception) {
            // API failed — return empty list instead of crashing
            return emptyList()
        }

        // Handle null Search or empty results
        val searchResults = response.Search ?: return emptyList()

        // Map to DB entities & save
        val entities = searchResults.map { it.toEntity(finalQuery) }
        dao.insertMovies(entities)

        return entities.map { it.toDomain() }
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetail {
        // Check local cache first
        val cached = dao.getMovieDetail(imdbId)
        if (cached != null) return cached.toDomain()

        // Call API
        val response = api.getMovieDetail(imdbId)

        // Save in DB
        val entity = response.toEntity()
        dao.insertMovieDetail(entity)

        return entity.toDomain()
    }
}
