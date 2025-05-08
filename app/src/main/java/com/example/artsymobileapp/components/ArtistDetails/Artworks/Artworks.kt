package com.example.artsymobileapp.components.ArtistDetails.Artworks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.artsymobileapp.components.ArtistDetails.Category.CategoryDialog
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel

val card_container = Modifier
    .fillMaxWidth()
    .height(600.dp)

val card_content = Modifier.fillMaxSize()

val card_image = Modifier
    .fillMaxWidth()
    .fillMaxHeight(0.75f)

val card_body_container = Modifier
    .fillMaxWidth()
    .padding(20.dp)

val card_title = TextStyle(
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = 32.sp
)


@Composable
fun Artworks(id: String, image: String, title: String, date: String, viewModel: ArtsyViewModel) {

    var showCategories by rememberSaveable  { mutableStateOf(false) }


    Card(modifier = card_container) {
        Column(modifier = card_content) {
            AsyncImage(
                model = image,
                contentDescription = "artwork image",
                modifier = card_image,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = card_body_container,
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "$title, $date", style = card_title, textAlign = TextAlign.Center)
                Button(onClick = {
                    showCategories = true
                    viewModel.clearCategoryList()
                }) {
                    Text(
                        text = "View Categories",
                    )
                }
            }
        }

    }

    if (showCategories) {
        CategoryDialog(artworkId = id, handleClose = { showCategories = false },viewModel=viewModel, categoryDwtails = viewModel.categoryUIState)
    }

}