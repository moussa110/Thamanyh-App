package com.mousa.thamnyahapp.data.remote.api

import com.mousa.thamnyahapp.data.remote.response.search.SearchSectionsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {
    @GET("search")
    suspend fun search(@Query("q") query: String): Response<SearchSectionsResponse>
}