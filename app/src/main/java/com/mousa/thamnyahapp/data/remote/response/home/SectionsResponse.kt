package com.mousa.thamnyahapp.data.remote.response.home


import com.squareup.moshi.Json

data class SectionsResponse(
    @Json(name = "pagination")
    val paginationResponse: PaginationResponse,
    @Json(name = "sections")
    val sectionResponses: List<SectionResponse>
)