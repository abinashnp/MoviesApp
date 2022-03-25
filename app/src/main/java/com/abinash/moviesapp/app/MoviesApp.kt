package com.abinash.moviesapp.app

import com.abinash.moviesapp.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class MoviesApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }


//    @Inject
//    lateinit var translationRepository: TranslationRepository
//
//    @Inject
//    lateinit var connectivityService: ConnectivityService


}