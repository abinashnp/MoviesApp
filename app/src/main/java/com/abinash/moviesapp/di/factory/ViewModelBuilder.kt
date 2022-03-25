package com.abinash.moviesapp.di.factory

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module


@Module
internal abstract class ViewModelBuilder {

    @Binds
    internal abstract fun bindViewModelFactory(factory: DaggerAwareViewModelFactory):
            ViewModelProvider.Factory
}