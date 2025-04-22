package com.example.artsymobileapp.components.network.types.categoryType

data class Links(
    val artists: Artists,
    val artworks: Artworks,
    val image: Image,
    val permalink: Permalink,
    val published_artworks: PublishedArtworks,
    val self: Self,
    val thumbnail: Thumbnail
)