package com.example.artsymobileapp.components.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.artsymobileapp.components.ArtistDetails.ArtistDetailsTabs
import com.example.artsymobileapp.components.Topbar.ArtistDetailsBar

@Composable
fun ArtistDetails(artistId: String, artistName: String) {

    Scaffold(
        topBar = { ArtistDetailsBar(artistName = artistName) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            ArtistDetailsTabs()
        }
    }

}