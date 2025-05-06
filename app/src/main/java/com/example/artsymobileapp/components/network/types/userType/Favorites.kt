package com.example.artsymobileapp.components.network.types.userType

import java.util.Date

data class Favorites(
    val _id: String,
    val artistId: String,
    val artistName: String,
    val birthDay: String,
    val createdAt: Date,
    val deathDay: String,
    val image: String,
    val nationality: String
)