package com.mousa.thamnyahapp.data.remote.api

import com.mousa.thamnyahapp.data.remote.response.SectionsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApiService {
    @GET("home_sections")
    suspend fun getHomeSections(
        @Query("page") page: Int
    ): SectionsResponse
}