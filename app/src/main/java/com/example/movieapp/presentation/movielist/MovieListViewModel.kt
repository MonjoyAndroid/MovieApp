package com.example.movieapp.presentation.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.IMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: IMovieRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MovieListState())
    val state: StateFlow<MovieListState> = _state

    init {
        loadDefaultMovies()
    }

    companion object {
        // This should be a keyword that returns a wide variety of movies
        const val DEFAULT_SEARCH = "Marvel"
    }

    fun onSearchQueryChanged(query: String) {
        _state.value = _state.value.copy(searchQuery = query)
        if (query.isNotEmpty()) {
            searchMovies(query)
        }else{
            loadDefaultMovies()
        }
    }

    private fun searchMovies(query: String) {
        viewModelScope.launch {
            val movies = repository.searchMovies(query)
            _state.value = _state.value.copy(movies = movies)
        }
    }

    private fun loadDefaultMovies() {
        viewModelScope.launch {
            val movies = repository.searchMovies(DEFAULT_SEARCH)
            _state.value = _state.value.copy(
                movies = movies,
                searchQuery = "" // Keep UI input empty but retain list
            )
        }
    }

}

data class MovieListState(
    val searchQuery: String = "",
    val movies: List<Movie> = emptyList()
)
