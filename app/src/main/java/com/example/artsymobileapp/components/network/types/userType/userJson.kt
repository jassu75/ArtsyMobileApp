package com.example.artsymobileapp.components.network.types.userType

data class userJson(
    val authenticated: Boolean,
    val favoritesList: List<Favorites>,
    val user: UserType
)