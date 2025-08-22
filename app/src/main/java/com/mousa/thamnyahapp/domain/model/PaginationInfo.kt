package com.mousa.thamnyahapp.domain.model

data class PaginationInfo(
    val nextPage: String? = null,
    val totalPages: Int = 0,
    val currentPage: Int = 1,
    val error: String? = null
) {
    val hasNextPage: Boolean
        get() = nextPage != null && currentPage < totalPages
    
    val isLastPage: Boolean
        get() = currentPage >= totalPages
}