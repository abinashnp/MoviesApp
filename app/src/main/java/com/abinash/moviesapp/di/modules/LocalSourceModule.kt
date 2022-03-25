package com.abinash.moviesapp.di.modules

import android.content.Context
import com.abinash.moviesapp.di.data.source.local.*
import com.abinash.moviesapp.di.data.source.local.database.IMDbDatabase
import com.abinash.moviesapp.di.data.source.local.database.MovieDao
import com.abinash.moviesapp.di.data.source.local.prefs.PrefsHelper
import com.abinash.moviesapp.di.scopes.AppScoped
import dagger.Module
import dagger.Provides


@Module
class LocalSourceModule {

    @AppScoped
    @Provides
    fun provideAppDatabase(context: Context): IMDbDatabase =
        IMDbDatabase.getInstance(context)

    @AppScoped
    @Provides
    fun provideMovieDao(database: IMDbDatabase): MovieDao =
        database.movieDao()

    @AppScoped
    @Provides
    fun providePrefsHelper(context: Context) = PrefsHelper(context)

    @AppScoped
    @Provides
    fun provideUserLocalDataSource(movieDao: MovieDao, context: Context) = MovieLocalDataSource(movieDao, context)
}