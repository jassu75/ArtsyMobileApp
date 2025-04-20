@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.artsymobileapp.components.Topbar

import androidx.compose.material.icons.Icons
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

@Composable
fun HomepageBar(setIsSearching: (Boolean) -> Unit) {
    TopAppBar(
        title = {

            Text("Artsy Search")


        },
        actions = {

            IconButton(onClick = { setIsSearching(true) }) {
                Icon(
                    Icons.Default.Search, contentDescription = "Search"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    Icons.Default.Person, contentDescription = "Profile"
                )

            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
    )


}