package com.abinash.moviesapp.ui.main

import android.os.Bundle
import com.abinash.moviesapp.di.scopes.AppScoped
import com.abinash.moviesapp.ui.BaseViewModel
import com.abinash.moviesapp.ui.Navigator
import javax.inject.Inject

@AppScoped
class MainNavigationViewModel @Inject constructor(): BaseViewModel() {

    private lateinit var appNavigator: Navigator

    fun setNavigator(navigator: Navigator) {
        appNavigator = navigator
    }

    fun navigateTo(navigation: Int, bundle: Bundle? = null) {
        appNavigator.navigate(navigation, bundle)
    }


    fun onBackClick() {
        appNavigator.onBackClick()
    }

}