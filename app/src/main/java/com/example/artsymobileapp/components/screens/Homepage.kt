package com.example.artsymobileapp.components.screens

import ArtistSearchBar
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.artsymobileapp.components.Favorites.Favorites
import com.example.artsymobileapp.components.Topbar.HomepageBar
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel
import java.text.SimpleDateFormat

import java.util.Date
import java.util.Locale

private val favorites_container = Modifier
    .fillMaxWidth()
    .padding(vertical = 8.dp)

private val favorites_text = TextStyle(
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold
)

private val login_button = Modifier.padding(vertical = 32.dp)

private val date_container = Modifier
    .fillMaxWidth()
    .padding(horizontal = 16.dp)

@Composable
fun HomepageDate() {
    val pattern = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    val date = pattern.format(Date())
    Box(modifier = date_container) {
        Text(text = date)
    }
}


@Composable
fun Homepage(viewModel: ArtsyViewModel, navController: NavController) {
    var isSearching by rememberSaveable { mutableStateOf(false) }
    val authenticated = viewModel.authenticated.value
    val context = LocalContext.current


    if (isSearching) {
        ArtistSearchBar(
            isSearching = isSearching,
            viewModel = viewModel,
            setIsSearching = { isSearching = it },
            navController = navController
        )
    } else {

        Scaffold(
            topBar = {
                HomepageBar(
                    setIsSearching = { isSearching = it },
                    navController = navController,
                    viewModel = viewModel
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {

                HomepageDate()
                Box(
                    modifier = favorites_container.background(color = MaterialTheme.colorScheme.tertiaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Favorites", style = favorites_text)
                }


                if (authenticated) {

                    Favorites(
                        viewModel = viewModel,
                        favoritesListUIState = viewModel.favoritesListUIState,
                        navController = navController
                    )


                } else {
                    Box(modifier = login_button.align(Alignment.CenterHorizontally)) {
                        Button(onClick = { navController.navigate(route = screens.Login.name) }) {
                            Text("Log in to see Favorites")
                        }
                    }
                }


                Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    TextButton(onClick = {

                        val url = Intent(Intent.ACTION_VIEW)
                        url.data = Uri.parse("https://www.artsy.net/")
                        context.startActivity(url)
                    }) {
                        Text("Powered by Artsy")
                    }
                }


            }
        }


    }
}