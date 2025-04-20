package com.example.artsymobileapp.components.screens

import ArtistSearchBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.artsymobileapp.components.Topbar.HomepageBar
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel

val favorites_container = Modifier
    .fillMaxWidth()
    .background(color = Color(0xFFF2F2F2))
    .padding(vertical = 8.dp)

val favorites_text = TextStyle(
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold
)


@Composable
fun Homepage(viewModel: ArtsyViewModel, navController: NavController) {
    var isSearching by rememberSaveable { mutableStateOf(false) }
    if (isSearching) {
        ArtistSearchBar(
            isSearching = isSearching,
            viewModel = viewModel,
            setIsSearching = { isSearching = it },
            navController=navController
        )
    } else {
        Scaffold(
            topBar = {
                HomepageBar(setIsSearching = { isSearching = it })
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(vertical = 32.dp),
                verticalArrangement = Arrangement.spacedBy(50.dp),
            ) {

                Box(modifier = favorites_container, contentAlignment = Alignment.Center) {
                    Text("Favorites", style = favorites_text)
                }

                Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Button(onClick = {}) {
                        Text("Log in to see Favorites")
                    }
                }

                Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    TextButton(onClick = {}) {
                        Text("Powered by Artsy")
                    }
                }


            }
        }


    }
}