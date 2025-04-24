package com.example.artsymobileapp.components.network

import ArtistListType
import com.example.artsymobileapp.components.network.types.artistDetailsType.ArtistDDetailsType
import com.example.artsymobileapp.components.network.types.categoryType.CategoryType
import com.example.artsymobileapp.components.network.types.userType.UserType

sealed interface ArtistListLoadingState {
    data class Success(val artistList: List<ArtistListType>) : ArtistListLoadingState
    object Error : ArtistListLoadingState
    object Loading : ArtistListLoadingState
}

sealed interface ArtistDetailsLoadingState {
    data class Success(val artistDetails: ArtistDDetailsType) : ArtistDetailsLoadingState
    object Error : ArtistDetailsLoadingState
    object Loading : ArtistDetailsLoadingState
}

sealed interface CategoryLoadingState {
    data class Success(val category: List<CategoryType>) : CategoryLoadingState
    object Error : CategoryLoadingState
    object Loading : CategoryLoadingState
}