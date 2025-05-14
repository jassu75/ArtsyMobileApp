package com.example.artsymobileapp.components.ArtistDetails.Artworks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel
import com.example.artsymobileapp.components.network.types.artistDetailsType.ArtworksType

private val artworks_container = Modifier.fillMaxSize()

private val artworks_content = Modifier
    .widthIn(max = 500.dp)
    .padding(horizontal = 20.dp, vertical = 20.dp)

@Composable
fun ArtworksList(artworklist: List<ArtworksType>, viewModel: ArtsyViewModel) {

    if (artworklist.isEmpty()) {
        EmptyArtworks()
    } else {
        Box(modifier = artworks_container, contentAlignment = Alignment.Center)
        {
            LazyColumn(
                modifier = artworks_content,
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                items(artworklist) { artwork ->
                    Box(modifier = Modifier.fillMaxWidth())
                    {
                        Artworks(
                            id = artwork.id,
                            title = artwork.title,
                            image = artwork.image,
                            date = artwork.date,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}