package com.mousa.thamnyahapp.data.remote.response.home


import com.squareup.moshi.Json

data class SectionResponse(
    @Json(name = "name") val name: String,
    @Json(name = "type") val type: String,
    @Json(name = "content_type") val contentType: String,
    @Json(name = "order") val order: Int,
    @Json(name = "content") val content: List<Map<String, Any>>
)