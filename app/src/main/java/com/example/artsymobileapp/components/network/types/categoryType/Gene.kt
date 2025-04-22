package com.example.artsymobileapp.components.network.types.categoryType

data class Gene(
    val _links: Links,
    val created_at: String,
    val description: String,
    val display_name: String,
    val id: String,
    val image_versions: List<String>,
    val name: String,
    val updated_at: String
)