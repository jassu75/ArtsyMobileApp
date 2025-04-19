package com.example.artsymobileapp.components.ArtistList

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.artsymobileapp.R

val card_container = Modifier
    .fillMaxWidth()
    .height(200.dp)

val card_content = Modifier.fillMaxSize()

val card_image = Modifier
    .fillMaxSize()

val card_body_container = Modifier
    .fillMaxWidth()
    .height(50.dp)
    .background(Color(0x80DEE6FD))


val card_body_content = Modifier
    .fillMaxSize()
    .padding(horizontal = 16.dp)

val card_title = TextStyle(
    fontSize = 24.sp,
    fontWeight = FontWeight.Bold
)


@Composable
fun ArtistCard(id: String, title: String, image: String) {
    Card(modifier = card_container) {
        Box(modifier = card_content) {
            if (image == "/assets/artsy_logo.svg") {
                Image(
                    painter = painterResource(id = R.drawable.artsy_logo),
                    contentDescription = "artsy_logo",
                    modifier = card_image,
                    contentScale = ContentScale.Crop
                )
            } else {
                AsyncImage(
                    model = image,
                    contentDescription = "artist image",
                    modifier = card_image,
                    contentScale = ContentScale.Crop

                )
            }

            Box(
                modifier = card_body_container.align(Alignment.BottomCenter)
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