package com.example.artsymobileapp.components.network.types.artistlisttype

data class ArtistListTypeItem(
    val _links: Links,
    val description: Any,
    val og_type: String,
    val title: String,
    val type: String
)