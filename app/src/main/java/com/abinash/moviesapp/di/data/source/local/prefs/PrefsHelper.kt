package com.abinash.moviesapp.di.data.source.local.prefs

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.abinash.moviesapp.utils.DEFAULT_API_KEY

private const val KEY_USER_ID = "UID"
private const val KEY_API = "KEY_API"

@SuppressLint("NewApi")
class PrefsHelper (context: Context) {

    private var prefs: SharedPreferences

    companion object {
        private const val KEY_SIZE = 256
        private lateinit var instance: PrefsHelper

        fun getInstance(): PrefsHelper {
            if (Companion::instance.isInitialized) {
                return instance
            }

            throw UninitializedPropertyAccessException(
                "Call init(Context) before using this method."
            )
        }

    }

    init {
        prefs = context.getSharedPreferences("ImdbPrefs", Context.MODE_PRIVATE)
    }

    var userId: String?
        get() = prefs.getString(KEY_USER_ID, null)
        set(value) = prefs.edit { putString(KEY_USER_ID, value) }

    var token: String
        get() = prefs.getString(KEY_API, DEFAULT_API_KEY) ?: run { ""}
        set(value) = prefs.edit { putString(KEY_API, value) }

}