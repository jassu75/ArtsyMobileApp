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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.artsymobileapp.components.ArtistDetails.Artworks.ArtworksList
import com.example.artsymobileapp.components.network.ArtistDetailsLoadingState
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel

val icon_container = Modifier.fillMaxWidth()

@Composable
fun ArtistDetailsTabs(
    artistDetails: ArtistDetailsLoadingState,
    viewModel: ArtsyViewModel
) {

    val tabs = listOf("ArtistInfo", "Artworks")
    var currentTab by remember { mutableStateOf("ArtistInfo") }

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
            }
        }
    }

}