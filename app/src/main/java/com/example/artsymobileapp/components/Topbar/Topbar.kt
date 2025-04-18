@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.artsymobileapp.components.Topbar

import ArtistSearchBar
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel

@Composable
fun TopBar(viewModel: ArtsyViewModel) {
    var isSearching by rememberSaveable { mutableStateOf(false) }

    if (isSearching) {
        ArtistSearchBar(
            isSearching = isSearching,
            viewModel = viewModel,
            setIsSearching = { isSearching = it }
        )
    } else {
        TopAppBar(
            title = {

                Text("Artsy Search")


            },
            actions = {

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
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
        )
    }


}