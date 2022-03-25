package com.abinash.moviesapp.di.network.error

import java.io.IOException


class NoConnectivityException(override var message: String) : IOException(message){

}