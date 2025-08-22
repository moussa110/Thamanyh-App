package com.mousa.thamnyahapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mousa.thamnyahapp.data.mappers.HomeMapper
import com.mousa.thamnyahapp.data.paging.HomePagingSource
import com.mousa.thamnyahapp.data.remote.api.HomeApiService
import com.mousa.thamnyahapp.domain.model.HomeSection
import com.mousa.thamnyahapp.domain.model.PaginationInfo
import com.mousa.thamnyahapp.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiService: HomeApiService,
    private val homeMapper: HomeMapper
) : HomeRepository {
    
    override suspend fun getHomeSections(page: Int): Result<List<HomeSection>> {
        return try {
            val response = apiService.getHomeSections(page)
            Result.success(homeMapper.mapToHomeSections(response))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override fun getHomeSectionsStream(): Flow<PagingData<HomeSection>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                HomePagingSource(apiService, homeMapper)
            }
        ).flow
    }
    
    override suspend fun getPaginationInfo(page: Int): PaginationInfo {
        return try {
            val response = apiService.getHomeSections(page)
            PaginationInfo(
                nextPage = response.paginationResponse.nextPage,
                totalPages = response.paginationResponse.totalPages,
                currentPage = page
            )
        } catch (e: Exception) {
            PaginationInfo(error = e.message)
        }
    }
}