package com.abinash.moviesapp.di.data.source.remote.model

import com.google.gson.annotations.SerializedName


data class FilmDataDetails(
    @SerializedName("id") val id: String,
    @SerializedName("rank") val rank: String,
    @SerializedName("rankUpDown") val rankUpDown: String,
    @SerializedName("title") val title: String,
    @SerializedName("fullTitle") val fullTitle: String,
    @SerializedName("year") val year: String,
    @SerializedName("image") val image: String,
    @SerializedName("crew") val crew: String,
    @SerializedName("imDbRating") val IMDbRating: String,
    @SerializedName("imDbRatingCount") val IMDbRatingCount: String,
)
