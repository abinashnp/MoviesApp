package com.abinash.moviesapp.di.component

import androidx.lifecycle.ViewModel
import com.abinash.moviesapp.di.modules.RepositoryModule
import com.abinash.moviesapp.di.scopes.AppScoped
import dagger.Component

@AppScoped
@Component(modules = [RepositoryModule::class])
interface ViewModelInjector {

    fun inject(viewModel: ViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun repositoryModule(repositoryModule: RepositoryModule): Builder
    }
}