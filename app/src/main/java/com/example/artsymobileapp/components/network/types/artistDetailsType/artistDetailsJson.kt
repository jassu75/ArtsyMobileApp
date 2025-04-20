package com.example.artsymobileapp.components.network.types.artistDetailsType

data class ArtistDetailsJson(
    val artWorks: ArtWorks,
    val artistInfo: ArtistInfo,
    val similarArtistList: List<SimilarArtist>
)