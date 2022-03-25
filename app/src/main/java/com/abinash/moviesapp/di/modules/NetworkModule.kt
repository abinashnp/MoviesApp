package com.abinash.moviesapp.di.modules

import android.content.Context
import com.abinash.moviesapp.di.network.ConnectivityInterceptor
import com.abinash.moviesapp.di.network.NetManager
import com.abinash.moviesapp.di.network.ServiceGenerator
import com.abinash.moviesapp.di.scopes.AppScoped
import com.abinash.moviesapp.utils.REST_API_ENDPOINT
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.abinash.moviesapp.di.data.source.remote.api.http.ImdbApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject


@Module
class NetworkModule @Inject constructor() {
    @AppScoped
    @Provides
    internal fun networkManager(context: Context): NetManager {
        return NetManager(context)
    }

    @AppScoped
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @AppScoped
    @Provides
    internal fun connectivityInterceptor(netManager: NetManager): Interceptor {
        return ConnectivityInterceptor(
            netManager
        )
    }
//  @AppScoped
//    @Provides
//    internal fun interceptorChanging(): Interceptor {
//        return InterceptorChanging()
//    }

    @AppScoped
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }


    @Provides
    @Reusable
    internal fun provideApi(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        connectivityInterceptor: ConnectivityInterceptor,
        // local: UserLocalDataSource,
        gson: Gson
    ): ImdbApi {

        val interceptors = arrayOf(
            httpLoggingInterceptor,
            connectivityInterceptor
        )

        return ServiceGenerator.generate(
            REST_API_ENDPOINT,
            ImdbApi::class.java,
            gson,
            null,
            interceptors
        )
    }
}