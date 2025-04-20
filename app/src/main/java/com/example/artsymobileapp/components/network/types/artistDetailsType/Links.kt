package com.example.artsymobileapp.components.network.types.artistDetailsType

data class Links(
    val artists: Artists,
    val collection_users: CollectionUsers,
    val genes: Genes,
    val image: Image,
    val partner: Partner,
    val permalink: Permalink,
    val sale_artworks: SaleArtworks,
    val self: Self,
    val similar_artworks: SimilarArtworks,
    val thumbnail: Thumbnail
)