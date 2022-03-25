package com.abinash.moviesapp.di.data.source.remote

import com.abinash.moviesapp.di.scopes.AppScoped
import com.abinash.moviesapp.di.data.source.local.prefs.PrefsHelper
import com.abinash.moviesapp.di.data.source.remote.api.http.ImdbApi
import javax.inject.Inject

private const val KEY_EMAIL = "email"
private const val KEY_PASSWORD = "password"
private const val KEY_DDID = "ddid"
private const val KEY_LANGUAGE = "language"

@AppScoped
class RemoteDataSource @Inject constructor(
    val api: ImdbApi,
    val prefsHelper: PrefsHelper
): BaseDataSource() {


    suspend fun getPopularMovies() = getResult { api.getMostPopularMovies(prefsHelper.token) }

    suspend fun getMovieImages(imdbId: String) = getResult { api.getMovieImages(prefsHelper.token, imdbId) }

}