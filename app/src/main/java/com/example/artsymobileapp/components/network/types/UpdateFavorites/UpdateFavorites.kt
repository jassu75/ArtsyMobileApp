package com.example.artsymobileapp.components.network.types.UpdateFavorites

import com.example.artsymobileapp.components.network.types.FavoritesType.FavoritesType

data class AddFavoritesType (
    val message:String,
    val favoritesList: FavoritesType?
)

data class DeleteFavoritesType (
    val message:String,
    val favoritesList: FavoritesType?
)