package com.example.artsymobileapp.components.network.types.FavoritesType

import java.util.Date

data class FavoritesTypeJsonItem(
    val _id: String,
    val artistId: String,
    val artistName: String,
    val birthDay: String,
    val createdAt: Date,
    val deathDay: String,
    val image: String,
    val nationality: String
)