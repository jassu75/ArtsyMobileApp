package com.example.artsymobileapp.components.network.types.artistDetailsType

data class ArtistInfoType(
    val artistName: String,
    val birthDay: String,
    val deathDay: String,
    val nationality: String,
    val biography: String,
)


data class ArtworksType(
    val id: String,
    val title: String,
    val date: String,
    val image: String,
)


data class ArtistDDetailsType(
    val artistInfo: ArtistInfoType,
    val artWorks: List<ArtworksType>
)