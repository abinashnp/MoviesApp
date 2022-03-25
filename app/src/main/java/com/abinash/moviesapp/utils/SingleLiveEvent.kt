package com.abinash.moviesapp.utils

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

//You can use LiveData independently from Room, but to do so you must manage data updates. However, LiveData has no publicly available methods to update the stored data.

//Therefore, if you want to update the stored data, you must use MutableLiveData instead of LiveData. The MutableLiveData class adds two public methods that allow you to set the value of a LiveData object: setValue(T) and postValue(T).

//MutableLiveData is usually used in the ViewModel, and then the ViewModel only exposes immutable LiveData objects to the observers.
//Usage of Mutable live data for setValue/postValue
class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val pending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner) { t ->
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        }
    }

    @MainThread
    override fun setValue(t: T?) {
        pending.set(true)
        super.setValue(t)
    }
   // Prevent LiveData from receive the last value when start observing
    @MainThread
    fun call() {
        value = null
    }
}

