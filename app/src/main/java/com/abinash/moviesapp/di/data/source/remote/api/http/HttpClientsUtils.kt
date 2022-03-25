package com.abinash.moviesapp.di.data.source.remote.api.http

import com.abinash.moviesapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

private const val CONNECTION_TIMEOUT = 40L
private const val WRITE_TIMEOUT = 40L
private const val READ_TIMEOUT = 40L

private val LOGGING_INTERCEPTOR = HttpLoggingInterceptor().apply {
    level = when (BuildConfig.DEBUG) {
        true -> HttpLoggingInterceptor.Level.BODY
        else -> HttpLoggingInterceptor.Level.NONE
    }
}

val apiClient = OkHttpClient.Builder()
    .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
    .addInterceptor(LOGGING_INTERCEPTOR)
    .build()