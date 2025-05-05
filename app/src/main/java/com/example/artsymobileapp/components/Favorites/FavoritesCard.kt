package com.example.artsymobileapp.components.Favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

private val card_container = Modifier.fillMaxWidth()
private val content_container = Modifier.fillMaxWidth()

@Composable
fun FavoritesCard(
    id: String,
    title: String,
    nationality: String,
    birthday: String,
    createdAt: String
) {

    Box(modifier = card_container) {

        Row(modifier = content_container) {
            Column {
                Text(text = title)
            }
        }
    }
}