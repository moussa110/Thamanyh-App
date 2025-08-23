package com.mousa.thamnyahapp.data.remote.datasource

import com.mousa.thamnyahapp.data.remote.api.HomeApiService
import com.mousa.thamnyahapp.data.remote.response.SectionsResponse
import com.mousa.thamnyahapp.data.remote.utils.ResultWrapper
import com.mousa.thamnyahapp.data.remote.utils.safeApiCall
import javax.inject.Inject

class HomeSectionsDatasource @Inject constructor(
    private val apiService: HomeApiService

) {
    suspend fun getHomeSections(page: Int): ResultWrapper<SectionsResponse> {
        return safeApiCall {
            apiService.getHomeSections(page)
        }
    }
}