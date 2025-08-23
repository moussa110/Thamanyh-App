package com.mousa.thamnyahapp.data.remote.response.search


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchSectionsResponse(
    val sections: List<SearchSectionResponse>
)