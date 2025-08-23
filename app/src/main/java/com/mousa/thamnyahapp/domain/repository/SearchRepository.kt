package com.mousa.thamnyahapp.domain.repository

import com.mousa.thamnyahapp.data.remote.utils.ResultWrapper
import com.mousa.thamnyahapp.domain.model.HomeSection

interface SearchRepository {
    suspend fun search(query: String): ResultWrapper<List<HomeSection>>
}