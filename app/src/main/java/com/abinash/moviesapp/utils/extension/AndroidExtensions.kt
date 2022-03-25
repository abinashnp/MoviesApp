package com.abinash.moviesapp.utils.extension

import android.content.Context
import android.content.Intent
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.net.Uri
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity


fun Fragment.showMessageSnackBar(message: String) {
    val messageSnack = Snackbar.make(this.requireActivity().window.decorView, message, Snackbar.LENGTH_SHORT)
    messageSnack.show()
}

fun Fragment.showMessageToast(translatedString: String) {
    Toast.makeText(this.requireContext(), translatedString, Toast.LENGTH_LONG).show()
}

fun Context.getListOrientation(): Int {
    return when (this.resources.configuration.orientation) {
        ORIENTATION_PORTRAIT -> RecyclerView.HORIZONTAL
        else -> RecyclerView.VERTICAL
    }
}

inline fun <reified T : ViewModel> DaggerAppCompatActivity.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(
        this,
        factory
    ).get(T::class.java)
}

fun Context.openBrowser(url: String) {
    val i = Intent(Intent.ACTION_VIEW)
    i.data = Uri.parse(url)
    this.startActivity(i)
}

inline fun <reified T : ViewModel> Fragment.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(
        requireActivity(),
        factory
    ).get(T::class.java)
}