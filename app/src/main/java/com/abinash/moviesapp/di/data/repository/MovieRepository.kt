package com.abinash.moviesapp.di.data.repository

import com.abinash.moviesapp.di.data.model.Movie
import com.abinash.moviesapp.di.data.source.local.MovieLocalDataSource
import com.abinash.moviesapp.di.data.source.remote.RemoteDataSource
import com.abinash.moviesapp.di.network.NetManager
import com.abinash.moviesapp.utils.performGetOperation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieRepository(
    val remote: RemoteDataSource,
    val local: MovieLocalDataSource,
    val netManager: NetManager
) {

    fun getMovieList()= performGetOperation(
        databaseQuery = {local.getMovies()},
        networkCall = { remote.getPopularMovies()},
        saveCallResult = { local.insert(it.items)}
    )

    suspend fun getMovieImages(imdbId: String) = remote.getMovieImages(imdbId)

    fun updateMovie(movie: Movie) {
        GlobalScope.launch(Dispatchers.IO) { local.updateMove(movie) }
    }

}