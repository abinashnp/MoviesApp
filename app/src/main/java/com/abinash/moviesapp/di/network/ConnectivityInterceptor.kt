package com.abinash.moviesapp.di.network

import com.abinash.moviesapp.di.network.error.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ConnectivityInterceptor @Inject constructor(var netManager: NetManager): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        netManager.isConnectedToInternet.let {
            if (it) {
                return chain.proceed(chain.request().newBuilder().build())
            } else {
                throw NoConnectivityException(
                    "No connectivity exception"
                )
            }

        }
    }
}