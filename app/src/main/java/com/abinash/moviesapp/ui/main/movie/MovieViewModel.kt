package com.abinash.moviesapp.ui.main.movie

import com.abinash.moviesapp.di.data.repository.MovieRepository
import com.abinash.moviesapp.di.data.source.remote.model.FilmImagesData
import com.abinash.moviesapp.ui.BaseViewModel
import com.abinash.moviesapp.utils.Resource
import com.abinash.moviesapp.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    val movieRepository: MovieRepository
): BaseViewModel() {

    val movieResult = SingleLiveEvent<Resource<FilmImagesData>>()

    fun getMovieImages(imdbId: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val result = movieRepository.getMovieImages(imdbId)
            movieResult.postValue(result)
        }
    }
}