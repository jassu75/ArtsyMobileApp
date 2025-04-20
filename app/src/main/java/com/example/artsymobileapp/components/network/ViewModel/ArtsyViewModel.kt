package com.example.artsymobileapp.components.network.ViewModel

import ArtistListType
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artsymobileapp.components.network.ArtistListLoadingState
import com.example.artsymobileapp.components.network.ArtsyAPI
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay


class ArtsyViewModel : ViewModel() {

    private var searchJob: Job?=null

    var artistListUiState: ArtistListLoadingState by mutableStateOf(ArtistListLoadingState.Loading)
        private set

    fun getArtistList(artistName: String) {
        searchJob?.cancel()
        searchJob=viewModelScope.launch {
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
}