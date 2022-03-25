package com.abinash.moviesapp.ui

import android.os.Bundle
import androidx.navigation.NavDirections


interface Navigator {

    fun onBackClick()

    fun navigate(navigateAction: Int, bundle: Bundle? = null)

    fun navigate(navigateAction: NavDirections)

}