package com.mousa.thamnyahapp.data.remote.response.search

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchContentResponse(
    @Json(name = "podcast_id") val podcastId: String,
    val name: String,
    val description: String,
    @Json(name = "avatar_url") val avatarUrl: String,
    @Json(name = "episode_count") val episodeCount: String,
    val duration: String,
    val language: String?,
    val priority: String?,
    val popularityScore: String?,
    val score: String?
)