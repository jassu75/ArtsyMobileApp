package com.example.artsymobileapp.components.ArtistDetails


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artsymobileapp.components.network.types.artistDetailsType.ArtistInfoType


val spacing_below_title = Modifier.height(8.dp)
val spacing_below_nationality = Modifier.height(16.dp)

val title_text = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
val info_text = TextStyle(fontSize = 16.sp)
val bio_text = TextStyle(fontSize = 16.sp)


@Composable
fun ArtistInfo(artistInfo: ArtistInfoType) {
    val cleanedBio =
        artistInfo.biography.replace(Regex("-\\s+"), "").replace(Regex("(?<!\n)\n(?!\n)"), " ")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp, horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = artistInfo.artistName, style = title_text)
        Spacer(modifier = spacing_below_title)
        Text(
            text = "${artistInfo.nationality}, ${artistInfo.birthDay} - ${artistInfo.deathDay} ",
            style = info_text
        )
        Spacer(modifier = spacing_below_nationality)
        Text(
            text = cleanedBio,
            style = bio_text
        )


    }
}