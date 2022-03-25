package com.abinash.moviesapp.ui.main.movielist

import androidx.lifecycle.LiveData
import com.abinash.moviesapp.di.data.model.Movie
import com.abinash.moviesapp.di.data.repository.MovieRepository
import com.abinash.moviesapp.ui.BaseViewModel
import com.abinash.moviesapp.utils.Resource
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    val movieRepository: MovieRepository
): BaseViewModel() {


    val movieList: LiveData<Resource<List<Movie>>>
        get() = loadMovieList()

    fun loadMovieList(): LiveData<Resource<List<Movie>>> {
        return movieRepository.getMovieList()
    }

    fun updateMovie(movie: Movie) {
        movieRepository.updateMovie(movie)
    }
}