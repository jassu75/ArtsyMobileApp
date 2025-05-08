@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.artsymobileapp.components.Topbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.artsymobileapp.components.Favorites.FavoritesIcon
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel

private val heading_text = TextStyle(fontSize = 20.sp)
private val topbar_container = Modifier.fillMaxSize()

@Composable
fun ArtistDetailsBar(
    artistName: String,
    navController: NavController,
    viewModel: ArtsyViewModel,
    favoritesIdList: List<String>,
    artistId: String
) {

    val authenticated = viewModel.authenticated.value
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = topbar_container

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            Icons.Default.ArrowBack, contentDescription = "Back Arrow"
                        )
                    }
                    Text(artistName, style = heading_text)

                }
                if (authenticated) {
                    FavoritesIcon(
                        viewModel = viewModel,
                        favoritesIdList = favoritesIdList,
                        artistId = artistId
                    )
                }
            }

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
    )
}