package com.example.artsymobileapp.components.Favorites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.artsymobileapp.components.network.FavoritesLoadingState
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel

@Composable
fun Favorites(
    viewModel: ArtsyViewModel,
    navController: NavController,
    favoritesListUIState: FavoritesLoadingState
) {
    val user = viewModel.user.value

    LaunchedEffect(Unit) {
        if (user != null) {
            viewModel.retrieveFavorites(user.email)
        }
    }

    if (favoritesListUIState is FavoritesLoadingState.Success) {
        val favoritesList = favoritesListUIState.favoritesList
        if (favoritesList.size == 0) {
            EmptyFavorites()
        } else {

            FavoritesList(favoritesList = favoritesList)
        }
    } else if (favoritesListUIState is FavoritesLoadingState.Error) {
        EmptyFavorites()
    }


}