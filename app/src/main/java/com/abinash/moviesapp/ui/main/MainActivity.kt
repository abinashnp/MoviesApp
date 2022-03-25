package com.abinash.moviesapp.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.abinash.moviesapp.R
import com.abinash.moviesapp.databinding.ActivityMainBinding
import com.abinash.moviesapp.ui.Navigator
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var navController: NavController
    private lateinit var navViewModel: MainNavigationViewModel

    companion object {
        fun goToMainActivityClearTop(context: Context) {

            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
        fun goToMainActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initiateViewModel()
        initNavigation()
    }

    //NAVIGATION METHODS
    private fun initNavigation() {
        navController = Navigation.findNavController(this, R.id.nav_main_activity)
    }

    private fun initiateViewModel() {
        navViewModel =
            ViewModelProvider(this, viewModelFactory).get(MainNavigationViewModel::class.java)
        navViewModel.setNavigator(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onBackClick() {
        onBackPressed()
    }

    override fun navigate(navigateAction: Int, bundle: Bundle?) {
        navController.navigate(navigateAction, bundle)
    }

    override fun navigate(navigateAction: NavDirections) {

        navController.navigate(navigateAction)
    }


}