package com.mousa.thamnyahapp.data.remote.response.home


import com.squareup.moshi.Json

data class PaginationResponse(
    @Json(name = "next_page")
    val nextPage: String,
    @Json(name = "total_pages")
    val totalPages: Int
)