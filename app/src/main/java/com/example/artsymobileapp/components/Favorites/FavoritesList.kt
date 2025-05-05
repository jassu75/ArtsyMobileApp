package com.example.artsymobileapp.components.Favorites


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.artsymobileapp.components.network.types.FavoritesType.FavoritesType
import androidx.compose.foundation.lazy.items

private val favorites_container = Modifier.fillMaxWidth()

@Composable
fun FavoritesList(favoritesList: FavoritesType) {

    LazyColumn(
        modifier = favorites_container,
    ) {
        items(favoritesList) { favorite ->
            FavoritesCard(
                id = favorite.artistId,
                title = favorite.artistName,
                createdAt = favorite.createdAt,
                nationality = favorite.nationality,
                birthday = favorite.birthDay
            )

        }
    }

}