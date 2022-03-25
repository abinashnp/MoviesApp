package com.abinash.moviesapp.di.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "movies"
)
data class MovieDB (
    @PrimaryKey
    val id: String,
    val rank: String,
    val rankUpDown: String,
    val title: String,
    val fullTitle: String,
    val year: String,
    val image: String,
    val crew: String,
    val IMDbRating: String,
    val IMDbRatingCount: String,
    val watched: Boolean
)