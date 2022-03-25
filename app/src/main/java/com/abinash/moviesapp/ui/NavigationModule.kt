package com.abinash.moviesapp.ui

import androidx.lifecycle.ViewModel
import com.abinash.moviesapp.di.factory.ViewModelKey
import com.abinash.moviesapp.ui.main.MainNavigationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class NavigationModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainNavigationViewModel::class)
    abstract fun bindMainNavigationViewModel(viewModel: MainNavigationViewModel): ViewModel
}