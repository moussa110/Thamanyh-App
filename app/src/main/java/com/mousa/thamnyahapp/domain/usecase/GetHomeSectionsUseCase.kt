package com.mousa.thamnyahapp.domain.usecase

import androidx.paging.PagingData
import com.mousa.thamnyahapp.domain.model.HomeSection
import com.mousa.thamnyahapp.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomeSectionsUseCase @Inject constructor(private val repository: HomeRepository) {
    operator fun invoke():Flow<PagingData<HomeSection>> = repository.getHomeSectionsStream()
}