package com.abinash.moviesapp.di.modules

import com.abinash.moviesapp.di.data.repository.*
import com.abinash.moviesapp.di.data.source.local.*
import com.abinash.moviesapp.di.data.source.local.prefs.PrefsHelper
import com.abinash.moviesapp.di.data.source.remote.RemoteDataSource
import com.abinash.moviesapp.di.data.source.remote.api.http.ImdbApi
import com.abinash.moviesapp.di.network.NetManager
import com.abinash.moviesapp.di.scopes.AppScoped
import dagger.Module
import dagger.Provides


@Module(
    includes = [LocalSourceModule::class]
)
class RepositoryModule {

    @AppScoped
    @Provides
    fun provideRemoteDataSource(api: ImdbApi, prefsHelper: PrefsHelper) = RemoteDataSource(api, prefsHelper)


    @AppScoped
    @Provides
    fun provideMovieRepository(
        remote: RemoteDataSource,
        local: MovieLocalDataSource,
        netManager: NetManager,
    ) = MovieRepository(remote, local, netManager)


}