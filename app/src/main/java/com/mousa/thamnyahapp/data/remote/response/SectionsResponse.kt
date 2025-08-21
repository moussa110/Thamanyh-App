package com.mousa.thamnyahapp.data.remote.response


import com.squareup.moshi.Json

data class SectionsResponse(
    @Json(name = "pagination")
    val paginationResponse: PaginationResponse,
    @Json(name = "sections")
    val sectionResponses: List<SectionResponse>
)