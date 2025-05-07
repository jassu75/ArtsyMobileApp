package com.example.artsymobileapp.components.ArtistDetails.SimilarArtists

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.artsymobileapp.R
import com.example.artsymobileapp.components.Favorites.FavoritesIcon
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel
import com.example.artsymobileapp.components.screens.screens

private val card_container = Modifier
    .fillMaxWidth()
    .height(200.dp)

private val card_content = Modifier.fillMaxSize()

private val card_image = Modifier
    .fillMaxSize()

private val card_body_container = Modifier
    .fillMaxWidth()
    .height(50.dp)


private val card_body_content = Modifier
    .fillMaxSize()
    .padding(horizontal = 16.dp)

private val card_title = TextStyle(
    fontSize = 24.sp,
    fontWeight = FontWeight.Bold
)

private val favorites_container = Modifier.padding(16.dp)


@Composable
fun SimilarArtistCard(
    id: String,
    title: String,
    image: String,
    navController: NavController,
    viewModel: ArtsyViewModel,
    favoritesIdList: List<String>

) {
    Card(modifier = card_container, onClick = {
        navController.navigate(route = "${screens.ArtistDetails.name}/${id}/${title}")
    }) {
        val authenticated = viewModel.authenticated.value

        Box(modifier = card_content) {
            if (image == "/assets/artsy_logo.svg") {
                Image(
                    painter = painterResource(id = R.drawable.artsy_logo),
                    contentDescription = "artsy_logo",
                    modifier = card_image,
                )
            } else {
                AsyncImage(
                    model = image,
                    contentDescription = "artist image",
                    modifier = card_image,
                    contentScale = ContentScale.Crop

                )
            }

            if (authenticated) {
                Box(
                    modifier = favorites_container
                        .align(Alignment.TopEnd)
                ) {
                    FavoritesIcon(
                        artistId = id,
                        favoritesIdList = favoritesIdList,
                        viewModel = viewModel
                    )
                }
            }

            Box(
                modifier = card_body_container
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .align(Alignment.BottomCenter)
            ) {
                Row(
                    modifier = card_body_content,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(title, style = card_title)
                    Icon(
                        Icons.Default.KeyboardArrowRight,
                        contentDescription = "Right Arrow"
                    )


                }
            }
        }

    }
}