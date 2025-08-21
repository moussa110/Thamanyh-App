package com.mousa.thamnyahapp.domain.model

data class AudioBookContent(
    override val id: String,
    override val name: String,
    val authorName: String,
    override val description: String,
    override val avatarUrl: String,
    override val duration: Int,
    val language: String,
    val releaseDate: String,
    override val score: Float
) : Content()