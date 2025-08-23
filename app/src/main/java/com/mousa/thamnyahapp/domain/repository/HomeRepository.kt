package com.mousa.thamnyahapp.domain.repository

import androidx.paging.PagingData
import com.mousa.thamnyahapp.domain.model.HomeSection
import com.mousa.thamnyahapp.domain.model.PaginationInfo
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getHomeSectionsStream(): Flow<PagingData<HomeSection>>
}