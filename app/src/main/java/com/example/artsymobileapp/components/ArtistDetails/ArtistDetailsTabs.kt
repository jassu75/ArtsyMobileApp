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
import androidx.compose.ui.tooling.preview.Preview

val icon_container = Modifier.fillMaxWidth()

@Preview
@Composable
fun ArtistDetailsTabs() {

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
    }

}