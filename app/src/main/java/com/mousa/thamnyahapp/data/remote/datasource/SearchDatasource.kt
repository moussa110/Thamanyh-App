package com.mousa.thamnyahapp.data.remote.datasource

import com.mousa.thamnyahapp.data.remote.api.SearchApiService
import com.mousa.thamnyahapp.data.remote.response.search.SearchSectionsResponse
import com.mousa.thamnyahapp.data.remote.utils.ResultWrapper
import com.mousa.thamnyahapp.data.remote.utils.safeApiCall
import com.mousa.thamnyahapp.di.SearchApi
import javax.inject.Inject

class SearchDatasource @Inject constructor(
   @SearchApi private val apiService: SearchApiService
) {
    suspend fun search(query: String): ResultWrapper<SearchSectionsResponse> {
        return safeApiCall {
            apiService.search(query)
        }
    }
}