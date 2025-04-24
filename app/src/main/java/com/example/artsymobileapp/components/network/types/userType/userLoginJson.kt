package com.example.artsymobileapp.components.network.types.userType

data class userLoginJson(
    val authenticated: Boolean,
    val favoritesList: List<Favorites>,
    val user: UserType
)