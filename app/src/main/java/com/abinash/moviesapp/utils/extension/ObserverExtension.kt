package com.abinash.moviesapp.utils.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
//            when((t as Resource<*>).status) {
//                Resource.Status.ERROR, Resource.Status.SUCCESS -> {
//
//                }
//                else ->{}
//            }
            removeObserver(this)
        }
    })
}

suspend fun <K, V> Flow<Pair<K, V>>.toMap(): Map<K, V> {
    val result = mutableMapOf<K, V>()
    collect(object : FlowCollector<Pair<K,V>> {
        override suspend fun emit(value: Pair<K,V>) {
           result.plus(value)
        }
    })
    return result
}


