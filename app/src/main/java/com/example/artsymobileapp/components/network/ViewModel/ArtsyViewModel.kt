package com.example.artsymobileapp.components.network.ViewModel

import ArtistListType
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artsymobileapp.components.network.ArtistDetailsLoadingState
import com.example.artsymobileapp.components.network.ArtistListLoadingState
import com.example.artsymobileapp.components.network.ArtsyAPI
import com.example.artsymobileapp.components.network.types.artistDetailsType.ArtistDDetailsType
import com.example.artsymobileapp.components.network.types.artistDetailsType.ArtistInfoType
import com.example.artsymobileapp.components.network.types.artistDetailsType.ArtworksType
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay


class ArtsyViewModel : ViewModel() {

    private var searchJob: Job? = null

    var artistListUiState: ArtistListLoadingState by mutableStateOf(ArtistListLoadingState.Loading)
        private set

    var artistDetailsUIState: ArtistDetailsLoadingState by mutableStateOf(ArtistDetailsLoadingState.Loading)
        private set

    fun getArtistList(artistName: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            if (artistName.length > 3) {
                delay(400)
                try {

                    artistListUiState = ArtistListLoadingState.Loading
                    val artistList = ArtsyAPI.retrofitService.getArtistList(artistName)
                    val refinedList = artistList.map { artistInfo ->

                        val image =
                            if (artistInfo._links.thumbnail.href == "/assets/shared/missing_image.png")
                                "/assets/artsy_logo.svg"
                            else
                                artistInfo._links.thumbnail.href

                        ArtistListType(
                            id = artistInfo._links.self.href?.split("/")?.last().orEmpty(),
                            title = artistInfo.title,
                            image = image
                        )
                    }
                    artistListUiState = ArtistListLoadingState.Success(refinedList)

                } catch (e: Exception) {
                    Log.e("FETCHARTISTLIST", "Error occured fetching artistList", e)
                    artistListUiState = ArtistListLoadingState.Error
                }
            } else {
                artistListUiState = ArtistListLoadingState.Success(emptyList())
            }
        }
    }

    fun getArtistDetails(artistId: String) {
        viewModelScope.launch {
            try {

                artistDetailsUIState = ArtistDetailsLoadingState.Loading
                val artistDetails = ArtsyAPI.retrofitService.getArtistDetails(artistId)

                val refinedArtistInfo = ArtistInfoType(
                    artistName = artistDetails.artistInfo.name,
                    birthDay = artistDetails.artistInfo.birthday,
                    deathDay = artistDetails.artistInfo.deathday,
                    nationality = artistDetails.artistInfo.nationality,
                    biography = artistDetails.artistInfo.biography
                )

                val refinedArtworks = artistDetails.artWorks._embedded.artworks.map { artworkInfo ->
                    ArtworksType(
                        id = artworkInfo.id,
                        title = artworkInfo.title,
                        date = artworkInfo.date,
                        image = artworkInfo._links.thumbnail.href
                    )
                }

                val refinedArtistDetails = ArtistDDetailsType(
                    artistInfo = refinedArtistInfo,
                    artWorks = refinedArtworks
                )

                artistDetailsUIState = ArtistDetailsLoadingState.Success(refinedArtistDetails)



            } catch (e: Exception) {
                Log.e("FETCHARTISTDETAILS", "Error occured fetching artist details", e)
                artistListUiState = ArtistListLoadingState.Error
            }

        }
    }
}