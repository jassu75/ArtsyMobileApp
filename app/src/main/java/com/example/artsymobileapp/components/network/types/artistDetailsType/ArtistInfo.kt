package com.example.artsymobileapp.components.network.types.artistDetailsType

data class ArtistInfo(
    val _links: LinksXX,
    val biography: String,
    val birthday: String,
    val created_at: String,
    val deathday: String,
    val gender: String,
    val hometown: String,
    val id: String,
    val image_versions: List<String>,
    val location: String,
    val name: String,
    val nationality: String,
    val slug: String,
    val sortable_name: String,
    val target_supply: Boolean,
    val updated_at: String
)