package com.abinash.moviesapp.di.data.source.remote.model

import com.google.gson.annotations.SerializedName


data class FilmImagesData(
    @SerializedName("imDbId") val imDbId: String,
    @SerializedName("title") val title: String,
    @SerializedName("fullTitle") val fullTitle: String,
    @SerializedName("type") val type: String,
    @SerializedName("year") val year: String,
    @SerializedName("items") val images: List<FilmImages>
)
