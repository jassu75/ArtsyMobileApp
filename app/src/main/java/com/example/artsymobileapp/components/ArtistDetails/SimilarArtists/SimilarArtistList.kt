package com.example.artsymobileapp.components.ArtistDetails.SimilarArtists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel
import com.example.artsymobileapp.components.network.types.artistDetailsType.SimilarArtistListType

private val similar_artistlist_content = Modifier
    .widthIn(max = 500.dp)
    .padding(horizontal = 20.dp, vertical = 20.dp)

private val similar_artistlist_container = Modifier.fillMaxSize()

@Composable
fun SimilarArtistList(
    similarArtistList: List<SimilarArtistListType>,
    navController: NavController,
    viewModel: ArtsyViewModel,

    ) {
    if (similarArtistList.isEmpty()) {
        EmptySimilarArtists()
    } else {
        Box(modifier = similar_artistlist_container, contentAlignment = Alignment.Center) {
            LazyColumn(
                modifier = similar_artistlist_content,
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                items(similarArtistList) { artistInfo ->
                    Box(modifier = Modifier.fillMaxWidth())
                    {
                        SimilarArtistCard(
                            id = artistInfo.id,
                            title = artistInfo.title,
                            image = artistInfo.image,
                            navController = navController,
                            viewModel = viewModel,
                            favoritesIdList = viewModel.favoriteIdsList,
                        )
                    }
                }
            }
        }
    }


}