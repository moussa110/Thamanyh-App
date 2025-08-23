package com.mousa.thamnyahapp.presentation.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mousa.thamnyahapp.data.remote.utils.ResultWrapper
import com.mousa.thamnyahapp.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _state = MutableStateFlow<SearchUiState>(SearchUiState.Empty)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _query
                .debounce(200)
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    if (query.isBlank()) {
                        flow { emit(ResultWrapper.Success(emptyList())) }
                    } else {
                        _state.value = SearchUiState.Loading
                        searchUseCase(query)
                    }
                }
                .collect { result ->
                    _state.value = when (result) {
                        is ResultWrapper.Success -> {
                            SearchUiState.Success(result.data)
                        }
                        is ResultWrapper.Error -> SearchUiState.Error(result.error.message ?: "Unexpected Error")
                    }
                }
        }
    }

    fun onQueryChange(newQuery: String) {
        _query.value = newQuery
    }
}