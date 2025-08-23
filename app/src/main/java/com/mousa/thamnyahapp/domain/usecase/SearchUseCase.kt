package com.mousa.thamnyahapp.domain.usecase

import com.mousa.thamnyahapp.data.remote.utils.ResultWrapper
import com.mousa.thamnyahapp.domain.model.HomeSection
import com.mousa.thamnyahapp.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    operator fun invoke(query: String): Flow<ResultWrapper<List<HomeSection>>> = flow {
        if (query.isBlank()) return@flow
        emit(ResultWrapper.Success(emptyList()))
        emit(repository.search(query))
    }
}