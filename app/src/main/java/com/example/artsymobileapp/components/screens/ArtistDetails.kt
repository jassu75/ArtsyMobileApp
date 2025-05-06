package com.example.artsymobileapp.components.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.artsymobileapp.components.ArtistDetails.ArtistDetailsTabs
import com.example.artsymobileapp.components.Topbar.ArtistDetailsBar
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel

@Composable
fun ArtistDetails(
    navController: NavController,
    viewModel: ArtsyViewModel,
    artistId: String,
    artistName: String
) {
    LaunchedEffect(artistId) {
        viewModel.getArtistDetails(artistId)
    }


    Scaffold(
        topBar = {
            ArtistDetailsBar(
                artistName = artistName,
                navController = navController,
                viewModel = viewModel,
                favoritesIdList = viewModel.favoriteIdsList,
                artistId=artistId

            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            ArtistDetailsTabs(
                artistDetails = viewModel.artistDetailsUIState,
                viewModel = viewModel,
                navController = navController
            )

        }
    }

}