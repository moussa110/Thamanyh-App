package com.mousa.thamnyahapp.data.remote.response.search

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchSectionResponse(
    val name: String,
    val type: String,
    @Json(name = "content_type") val contentType: String,
    val order: String,
    val content: List<SearchContentResponse>
)