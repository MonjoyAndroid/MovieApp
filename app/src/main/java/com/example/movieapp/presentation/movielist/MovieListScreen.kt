package com.example.movieapp.presentation.movielist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel,
    onMovieClick: (String) -> Unit
) {
    val state = viewModel.state.collectAsState()

    Column(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        OutlinedTextField(
            value = state.value.searchQuery,
            onValueChange = { viewModel.onSearchQueryChanged(it) },
            placeholder = { Text("Search movies") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        LazyVerticalGrid(columns = GridCells.Fixed(3), contentPadding = PaddingValues(8.dp)) {
            items(state.value.movies.size) { index ->
                val movie = state.value.movies[index]
                Column(
                    modifier = Modifier
                        .padding(4.dp)
                        .clickable { onMovieClick(movie.imdbId) }
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(movie.poster),
                        contentDescription = movie.title,
                        modifier = Modifier.height(150.dp).fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        movie.title,
                        color = Color.White,
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(movie.year, color = Color.Gray, fontSize = 10.sp)
                }
            }
        }
    }
}
