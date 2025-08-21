package com.mousa.thamnyahapp.domain.model

data class AudioArticleContent(
    override val id: String,
    override val name: String,
    val authorName: String,
    override val description: String,
    override val avatarUrl: String,
    override val duration: Int,
    val releaseDate: String,
    override val score: Float
) : Content()