package com.abinash.moviesapp.ui.main.movie

import androidx.lifecycle.ViewModel
import com.abinash.moviesapp.di.factory.ViewModelKey
import com.abinash.moviesapp.di.scopes.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MovieModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun movieFragment(): MovieFragment

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindMovieViewModel(viewModel: MovieViewModel): ViewModel

}