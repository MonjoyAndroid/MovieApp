package com.example.movieapp.presentation.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.model.MovieDetail
import com.example.movieapp.domain.repository.IMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: IMovieRepository
) : ViewModel() {

    private val _state = MutableStateFlow<MovieDetail?>(null)
    val state: StateFlow<MovieDetail?> = _state

    fun loadMovieDetail(imdbId: String) {
        viewModelScope.launch {
            val detail = repository.getMovieDetail(imdbId)
            _state.value = detail
        }
    }
}

