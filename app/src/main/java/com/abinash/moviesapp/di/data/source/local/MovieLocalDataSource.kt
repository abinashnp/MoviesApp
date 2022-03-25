package com.abinash.moviesapp.di.data.source.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.abinash.moviesapp.di.data.mapper.MovieMapper
import com.abinash.moviesapp.di.data.model.Movie
import com.abinash.moviesapp.di.data.source.local.database.MovieDao
import com.abinash.moviesapp.di.data.source.remote.model.FilmDataDetails

class MovieLocalDataSource(val movieDao: MovieDao, val context: Context) {

    fun getMovies(): LiveData<List<Movie>> {
        return movieDao.getAllMovies().map { list ->
            list.map { MovieMapper.fromLocalMovie(it)}
        }
    }

    suspend fun insert(items: List<FilmDataDetails>) {
        items.map{ MovieMapper.toLocalMovie(it) }.forEach { movieDao.insert(it) }

    }

    suspend fun updateMove(movie: Movie) {
        movieDao.update(MovieMapper.toLocalMovie(movie))
    }
//    suspend fun clearAll() {
//        database.layerDao().clearTable()
//        database.pictureDao().clearTable()
//    }
}