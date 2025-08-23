package com.mousa.thamnyahapp.presentation.screen.search

import com.mousa.thamnyahapp.domain.model.HomeSection

sealed class SearchUiState {
    object Loading : SearchUiState()
    object Empty : SearchUiState()
    data class Success(val sections: List<HomeSection>) : SearchUiState()
    data class Error(val message: String) : SearchUiState()
}