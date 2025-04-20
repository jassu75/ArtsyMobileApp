package com.example.artsymobileapp.components.network

import ArtistListType
import com.example.artsymobileapp.components.network.types.artistDetailsType.ArtistDDetailsType

sealed interface ArtistListLoadingState {
    data class Success(val artistList: List<ArtistListType>) : ArtistListLoadingState
    object Error : ArtistListLoadingState
    object Loading : ArtistListLoadingState
}

sealed interface ArtistDetailsLoadingState {
    data class Success(val artistDetails: List<ArtistDDetailsType>) : ArtistDetailsLoadingState
    object Error : ArtistDetailsLoadingState
    object Loading : ArtistDetailsLoadingState
}