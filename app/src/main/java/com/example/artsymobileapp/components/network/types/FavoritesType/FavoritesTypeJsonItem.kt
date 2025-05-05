package com.example.artsymobileapp.components.network.types.FavoritesType

data class FavoritesTypeJsonItem(
    val _id: String,
    val artistId: String,
    val artistName: String,
    val birthDay: String,
    val createdAt: String,
    val deathDay: String,
    val image: String,
    val nationality: String
)