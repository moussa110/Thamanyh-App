package com.mousa.thamnyahapp.data.repository

import com.mousa.thamnyahapp.data.mappers.HomeMapper
import com.mousa.thamnyahapp.data.mappers.toDomain
import com.mousa.thamnyahapp.data.remote.datasource.SearchDatasource
import com.mousa.thamnyahapp.data.remote.utils.ResultWrapper
import com.mousa.thamnyahapp.domain.model.HomeSection
import com.mousa.thamnyahapp.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val datasource: SearchDatasource
) :SearchRepository{
    override suspend fun search(query: String): ResultWrapper<List<HomeSection>> {
        return when (val result = datasource.search(query)) {
            is ResultWrapper.Success -> {
                val mapped = result.data.sections.map { it.toDomain() }
                ResultWrapper.Success(mapped)
            }
            is ResultWrapper.Error -> ResultWrapper.Error(result.error)
        }
    }
}