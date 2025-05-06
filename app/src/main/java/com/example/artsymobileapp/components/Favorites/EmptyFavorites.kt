package com.example.artsymobileapp.components.Favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


private val empty_favorites_container =
    Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))
        .background(color = Color(0xFFADD8E6))
        .padding(16.dp)

@Composable
fun EmptyFavorites() {
    Box(modifier = empty_favorites_container, contentAlignment = Alignment.Center) {
        Text(text = "No favorites")
    }
}