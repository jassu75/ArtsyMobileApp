@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.artsymobileapp.components.Topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel

@Composable
fun HomepageBar(viewModel: ArtsyViewModel,setIsSearching: (Boolean) -> Unit,navController: NavController) {

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
            Profile(navController=navController,viewModel=viewModel)

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
    )


}