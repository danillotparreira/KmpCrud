package br.com.parreira.kmpcrud.features.post.entity

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Int,
    val title: String,
    val body: String,
)