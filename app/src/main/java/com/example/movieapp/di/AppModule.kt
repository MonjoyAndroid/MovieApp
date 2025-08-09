package com.example.movieapp.di

import android.app.Application
import androidx.room.Room
import com.example.movieapp.data.local.MovieDatabase
import com.example.movieapp.data.remote.MovieApiService
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.domain.repository.IMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): MovieApiService {
        return Retrofit.Builder()
            .baseUrl("https://omdbapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): MovieDatabase {
        return Room.databaseBuilder(app, MovieDatabase::class.java, "movies_db").build()
    }

    @Provides
    @Singleton
    fun provideRepository(api: MovieApiService, db: MovieDatabase): IMovieRepository {
        return MovieRepository(api, db.movieDao())
    }
}
