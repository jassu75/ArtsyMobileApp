@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.artsymobileapp.components.Topbar

import ArtistSearchBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val topbar_container = Modifier
    .fillMaxWidth()
    .padding(horizontal = 12.dp)

val search_icon = Modifier.padding(top = 8.dp)
val close_icon = Modifier.padding(top = 8.dp)


@Preview
@Composable
fun TopBar() {
    var isSearching by rememberSaveable { mutableStateOf(false) }
    TopAppBar(
        title = {
            if (isSearching) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = topbar_container

                ) {
                    Box(modifier = search_icon) {
                        Icon(
                            Icons.Default.Search, contentDescription = "Search"
                        )
                    }


                    ArtistSearchBar(isSearching = isSearching)


                }
            } else {
                Text("Artsy Search")

            }
        },
        actions = {
            if (isSearching) {
                Box(modifier = close_icon) {
                    IconButton(onClick = {
                        isSearching = false
                    }) {
                        Icon(
                            Icons.Default.Clear, contentDescription = "Close"
                        )
                    }
                }
            } else {
                IconButton(onClick = { isSearching = true }) {
                    Icon(
                        Icons.Default.Search, contentDescription = "Search"
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Default.Person, contentDescription = "Profile"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
    )
}