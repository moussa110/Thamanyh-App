package com.mousa.thamnyahapp.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mousa.thamnyahapp.domain.model.HomeSection
import com.mousa.thamnyahapp.domain.usecase.GetHomeSectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getHomeSectionsUseCase: GetHomeSectionsUseCase
) : ViewModel() {

    val homeSections: Flow<PagingData<HomeSection>> =
        getHomeSectionsUseCase()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)
}