package com.example.artsymobileapp.components.Favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel
import com.example.artsymobileapp.R


fun handleFavoriteIconClick(
    artistId: String,
    setIsFavorite: (Boolean) -> Unit,
    favoritesIdList: List<String>,
    viewModel: ArtsyViewModel
) {
    if (artistId in favoritesIdList) {
        viewModel.removeFavoriteId(artistId = artistId)
        setIsFavorite(false)
    } else {
        viewModel.addFavoriteId(artistId = artistId)
        setIsFavorite(true)
    }
}

@Composable
fun FavoritesIcon(artistId: String, favoritesIdList: List<String>, viewModel: ArtsyViewModel) {

    var isFavorite: Boolean by rememberSaveable { mutableStateOf(artistId in favoritesIdList) }
    IconButton(
        onClick = {
            handleFavoriteIconClick(
                artistId = artistId,
                setIsFavorite = { isFavorite = it },
                favoritesIdList = favoritesIdList,
                viewModel = viewModel
            )
        },
        modifier = Modifier.background(
            color = MaterialTheme.colorScheme.tertiaryContainer,
            shape = CircleShape
        )
    ) {
        if (isFavorite) {
            Icon(imageVector = Icons.Default.Star, contentDescription = "Favorited Icon")
        } else {
            Icon(
                painter = painterResource(R.drawable.unfilled_star),
                contentDescription = "Unfavorited Icon"
            )
        }

    }
}