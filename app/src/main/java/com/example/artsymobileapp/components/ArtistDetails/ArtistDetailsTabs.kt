package com.example.artsymobileapp.components.ArtistDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.artsymobileapp.components.ArtistDetails.Artworks.ArtworksList
import com.example.artsymobileapp.components.SharedPreferences.readAuthenticated
import com.example.artsymobileapp.components.network.ArtistDetailsLoadingState
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel
import com.example.artsymobileapp.R
import com.example.artsymobileapp.components.ArtistDetails.SimilarArtists.SimilarArtistList


val icon_container = Modifier.fillMaxWidth()

@Composable
fun ArtistDetailsTabs(
    artistDetails: ArtistDetailsLoadingState,
    viewModel: ArtsyViewModel,
    navController: NavController
) {

    var currentTab by rememberSaveable { mutableStateOf("ArtistInfo") }
    val context = LocalContext.current
    val authenticated = readAuthenticated(context = context)
    val tabs = if (authenticated) {
        listOf("ArtistInfo", "Artworks", "SimilarArtists")
    } else {
        listOf("ArtistInfo", "Artworks")
    }


    Column(modifier = icon_container) {
        TabRow(selectedTabIndex = tabs.indexOf(currentTab)) {

            tabs.forEach { title ->
                Tab(
                    selected = currentTab == title,
                    onClick = { currentTab = title },
                    text = { Text(title) },
                    icon = {
                        when (title) {
                            "ArtistInfo" -> Icon(
                                Icons.Default.Info, contentDescription = "Artist Details"
                            )

                            "Artworks" -> Icon(
                                Icons.Default.AccountBox, contentDescription = "Artworks"
                            )

                            "SimilarArtists" -> Icon(
                                painter = painterResource(id = R.drawable.person_search),
                                contentDescription = "Similar artists"
                            )
                        }
                    }
                )
            }

        }

        if (artistDetails is ArtistDetailsLoadingState.Loading) {
            Loading()
        } else if (artistDetails is ArtistDetailsLoadingState.Success) {
            when (currentTab) {
                "ArtistInfo" -> ArtistInfo(
                    artistInfo = artistDetails.artistDetails.artistInfo,
                    viewModel = viewModel
                )

                "Artworks" -> ArtworksList(
                    artworklist = artistDetails.artistDetails.artWorks,
                    viewModel = viewModel
                )

                "SimilarArtists" -> SimilarArtistList(
                    similarArtistList = artistDetails.artistDetails.similarArtists,
                    navController = navController
                )
            }
        }
    }

}