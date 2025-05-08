package com.example.artsymobileapp.components.ArtistDetails.Artworks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

private val empty_artworks =
    Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))

private val empty_artworks_container = Modifier.padding(16.dp)

@Composable
fun EmptyArtworks() {

    Box(modifier = empty_artworks_container) {
        Box(
            modifier = empty_artworks
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .padding(16.dp), contentAlignment = Alignment.Center
        ) {
            Text(text = "No Artworks")
        }
    }

}