package com.android.simple.movie.list.app.di

import com.android.simple.movie.list.app.data.repositories.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton @Provides
    fun provideMoviesRepository() = MoviesRepository()
}