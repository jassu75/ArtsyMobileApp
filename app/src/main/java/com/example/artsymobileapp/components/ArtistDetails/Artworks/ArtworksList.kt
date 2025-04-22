package com.example.artsymobileapp.components.ArtistDetails.Artworks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.artsymobileapp.components.network.types.artistDetailsType.ArtworksType

val artworks_container = Modifier
    .fillMaxWidth()
    .padding(horizontal = 20.dp, vertical = 20.dp)

@Composable
fun Artworks(artworklist: List<ArtworksType>) {
    LazyColumn(
        modifier = artworks_container,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(artworklist) { artwork ->
            Box(modifier = Modifier.fillMaxWidth())
            {
                Artworks(
                    id = artwork.id,
                    title = artwork.title,
                    image = artwork.image,
                    date = artwork.date
                )
            }
        }
    }
}