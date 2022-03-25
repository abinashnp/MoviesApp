package com.abinash.moviesapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun <T, A> performGetOperation(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Resource<A>,
    saveCallResult: suspend (A) -> Unit
) =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            responseStatus.data?.let { saveCallResult(it) }

        } else if (responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
    }

fun <T> performPostOperationData(
    liveData: MutableLiveData<Resource<T>>,
    networkCall: suspend () -> Resource<T>,
    saveCallResult: (suspend (T) -> Unit?)? = null
) {
    liveData.postValue(Resource.loading())
    GlobalScope.launch {
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            liveData.postValue(Resource.success(responseStatus.data!!))
            saveCallResult?.let { it(responseStatus.data) }
        } else if (responseStatus.status == Resource.Status.ERROR) {
            liveData.postValue(Resource.error(responseStatus.message ?: "Unknown Error"))
        }
    }
}