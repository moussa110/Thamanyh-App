package com.mousa.thamnyahapp.data.paging

import androidx.core.net.toUri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mousa.thamnyahapp.data.mappers.HomeMapper
import com.mousa.thamnyahapp.data.remote.datasource.HomeSectionsDatasource
import com.mousa.thamnyahapp.domain.model.HomeSection

class HomePagingSource(
    private val datasource: HomeSectionsDatasource,
    private val homeMapper: HomeMapper
) : PagingSource<Int, HomeSection>() {
    
    override fun getRefreshKey(state: PagingState<Int, HomeSection>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
    
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeSection> {
        return try {
            val page = params.key ?: 1

            val response = datasource.getHomeSections(page)
            val homeSections = homeMapper.mapToHomeSections(response)

            val nextPage = response.paginationResponse.nextPage.let { nextPageUrl ->
                if (page>= response.paginationResponse.totalPages) {
                    null
                } else {
                    page+1
                }
            }

            LoadResult.Page(
                data = homeSections,
                prevKey = if (page == 1) null else page - 1,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    
    private fun extractPageNumberFromUrl(url: String): Int? {
        return try {
            val uri = url.toUri()
            val pageParam = uri.getQueryParameter("page")
            pageParam?.toInt()
        } catch (e: Exception) {
            null
        }
    }
}