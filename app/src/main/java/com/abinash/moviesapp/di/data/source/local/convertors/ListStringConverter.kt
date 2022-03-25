package com.abinash.moviesapp.di.data.source.local.convertors

import android.text.TextUtils
import androidx.room.TypeConverter

class ListStringConverter {
    @TypeConverter
    fun stringToList(list: MutableList<String>?): String? = when {
        list.isNullOrEmpty() -> null
        else -> TextUtils.join(",", list)
    }

    @TypeConverter
    fun stringToList(data: String?): MutableList<String> = when (data.isNullOrEmpty()) {
        true -> mutableListOf()
        else -> TextUtils.split(data, ",")?.toMutableList() ?: mutableListOf()
    }
}