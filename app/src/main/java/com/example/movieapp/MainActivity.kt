package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.MaterialTheme
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.presentation.moviedetails.MovieDetailScreen
import com.example.movieapp.presentation.moviedetails.MovieDetailViewModel
import com.example.movieapp.presentation.movielist.MovieListScreen
import com.example.movieapp.presentation.movielist.MovieListViewModel
import com.example.movieapp.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                    .padding(WindowInsets.safeDrawing.asPaddingValues()),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieApp()
                }

            }
        }
    }


    @Composable
    fun MovieApp() {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "movie_list"
        ) {
            // Movie List Screen
            composable("movie_list") {
                val viewModel: MovieListViewModel = hiltViewModel()
                MovieListScreen(
                    viewModel = viewModel,
                    onMovieClick = { imdbId ->
                        navController.navigate("movie_detail/$imdbId")
                    }
                )
            }

            // Movie Detail Screen
            composable(
                route = "movie_detail/{imdbId}",
                arguments = listOf(navArgument("imdbId") { type = NavType.StringType })
            ) { backStackEntry ->
                val imdbId = backStackEntry.arguments?.getString("imdbId") ?: ""
                val viewModel: MovieDetailViewModel = hiltViewModel()

                // Load movie detail when entering screen
                LaunchedEffect(imdbId) {
                    viewModel.loadMovieDetail(imdbId)
                }

                MovieDetailScreen(
                    viewModel = viewModel,
                    imdbId = imdbId,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
