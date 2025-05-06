package com.example.artsymobileapp.components.Favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.artsymobileapp.components.screens.screens
import java.util.Date


private val content_container = Modifier.fillMaxWidth(0.5f)
private val title_text = Modifier.fillMaxWidth()
private val info_text = Modifier.fillMaxWidth()

private val title_text_style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Medium)
private val info_text_style = TextStyle(fontSize = 16.sp)

fun handleFavoriteCardClick(id: String, navController: NavController, title: String) {
    navController.navigate(route = "${screens.ArtistDetails.name}/${id}/${title}")
}

@Composable
fun FavoritesCard(
    id: String,
    title: String,
    nationality: String,
    birthday: String,
    createdAt: Date,
    navController: NavController

) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                handleFavoriteCardClick(
                    id = id,
                    navController = navController,
                    title = title
                )
            }
            .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = content_container, verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = title,
                style = title_text_style,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = title_text
            )
            Text(
                text = "$nationality, $birthday",
                style = info_text_style,
                modifier = info_text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Column(verticalArrangement = Arrangement.Center) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Timer(createdAt = createdAt)
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = "Right Arrow"
                )
            }


        }
    }

}