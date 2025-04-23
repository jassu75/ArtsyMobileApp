package com.example.artsymobileapp.components.ArtistDetails.Category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

val card_container = Modifier
    .fillMaxWidth()
    .height(400.dp)

val card_content = Modifier.fillMaxSize()

val card_image = Modifier
    .fillMaxWidth()
    .fillMaxHeight(0.4f)

val card_body_container = Modifier
    .fillMaxWidth()
    .padding(10.dp)

val card_title = TextStyle(
    fontSize = 24.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = 24.sp
)

val description_text = TextStyle(fontSize = 18.sp)


@Composable
fun CategoryCard(id: String, title: String, image: String, description: String) {

    Card(modifier = card_container) {
        Column(modifier = card_content) {
            AsyncImage(
                model = image,
                contentDescription = "category image",
                modifier = card_image,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = card_body_container,
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = title, style = card_title, textAlign = TextAlign.Center)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(text = description, style = description_text)
                }

            }
        }

    }
}