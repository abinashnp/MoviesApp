package com.abinash.moviesapp.di.modules

import com.abinash.moviesapp.di.scopes.ActivityScoped
import com.abinash.moviesapp.ui.NavigationModule
import com.abinash.moviesapp.ui.main.MainActivity
import com.abinash.moviesapp.ui.main.movie.MovieModule
import com.abinash.moviesapp.ui.main.movielist.MovieListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            NavigationModule::class,
            MovieListModule::class,
            MovieModule::class
        ]
    )
    internal abstract fun mainActivity(): MainActivity

}