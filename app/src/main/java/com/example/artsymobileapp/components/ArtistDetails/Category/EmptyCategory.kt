package com.example.artsymobileapp.components.ArtistDetails.Category

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

private val empty_categories =
    Modifier
        .fillMaxWidth()

@Composable
fun EmptyCategories() {

    Box(
        modifier = empty_categories, contentAlignment = Alignment.Center
    ) {
        Text(text = "No Categories Available")
    }


}