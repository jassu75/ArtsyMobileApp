package com.example.artsymobileapp.components.Favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.artsymobileapp.components.network.FavoritesLoadingState
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel

private val favorites_container = Modifier.padding(horizontal = 8.dp)

@Composable
fun Favorites(
    viewModel: ArtsyViewModel,
    navController: NavController,
    favoritesListUIState: FavoritesLoadingState
) {
    Box(modifier = favorites_container)
    {
        if (favoritesListUIState is FavoritesLoadingState.Success) {
            val favoritesList = favoritesListUIState.favoritesList
            if (favoritesList.size == 0) {
                EmptyFavorites()
            } else {

                FavoritesList(favoritesList = favoritesList, navController = navController)
            }
        } else if (favoritesListUIState is FavoritesLoadingState.Error) {
            EmptyFavorites()
        }

    }
}