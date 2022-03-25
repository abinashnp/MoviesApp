package com.abinash.moviesapp.di.component

import android.app.Application
import com.abinash.moviesapp.app.MoviesApp
import com.abinash.moviesapp.di.factory.ViewModelBuilder
import com.abinash.moviesapp.di.modules.ActivityBindingModule
import com.abinash.moviesapp.di.modules.AppModule
import com.abinash.moviesapp.di.modules.NetworkModule
import com.abinash.moviesapp.di.modules.RepositoryModule
import com.abinash.moviesapp.di.scopes.AppScoped
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@AppScoped
@Component(
    modules = [AndroidInjectionModule::class,
        RepositoryModule::class,
        AppModule::class,
        ViewModelBuilder::class,
        ActivityBindingModule::class,
        NetworkModule::class,
//        RoomModule::class

    ]
)
interface AppComponent : AndroidInjector<MoviesApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}