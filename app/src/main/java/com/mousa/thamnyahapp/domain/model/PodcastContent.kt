package com.mousa.thamnyahapp.domain.model

data class PodcastContent(
    override val id: String,
    override val name: String,
    override val description: String,
    override val avatarUrl: String,
    val episodeCount: Int,
    override val duration: Int,
    val language: String?,
    val priority: Int,
    val popularityScore: Int,
    override val score: Float
) : Content()