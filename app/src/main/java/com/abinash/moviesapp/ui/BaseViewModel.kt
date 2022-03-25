package com.abinash.moviesapp.ui

import androidx.lifecycle.ViewModel
import com.abinash.moviesapp.di.component.ViewModelInjector
import com.abinash.moviesapp.di.modules.RepositoryModule
import com.abinash.moviesapp.utils.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineExceptionHandler
import com.abinash.moviesapp.di.component.DaggerViewModelInjector
import timber.log.Timber


abstract class BaseViewModel : ViewModel() {

    val onErrorEvent = SingleLiveEvent<String>()

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .repositoryModule(RepositoryModule())
        .build()

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
    }

    var disposable: CompositeDisposable = CompositeDisposable()

    init {
        inject()
    }

    private fun inject() {
        injector.inject(this)
    }

}