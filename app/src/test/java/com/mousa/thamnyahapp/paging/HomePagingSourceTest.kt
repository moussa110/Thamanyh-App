package com.mousa.thamnyahapp.paging

import androidx.paging.PagingSource
import com.mousa.thamnyahapp.data.mappers.HomeMapper
import com.mousa.thamnyahapp.data.remote.datasource.HomeSectionsDatasource
import com.mousa.thamnyahapp.data.remote.utils.NetworkError
import com.mousa.thamnyahapp.data.remote.utils.ResultWrapper
import com.mousa.thamnyahapp.domain.model.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import com.google.common.truth.Truth.assertThat
import com.mousa.thamnyahapp.data.paging.HomePagingSource
import com.mousa.thamnyahapp.data.remote.response.home.PaginationResponse
import com.mousa.thamnyahapp.data.remote.response.home.SectionsResponse

@ExperimentalCoroutinesApi
class HomePagingSourceTest {

    private lateinit var datasource: HomeSectionsDatasource
    private lateinit var mapper: HomeMapper
    private lateinit var pagingSource: HomePagingSource

    @Before
    fun setup() {
        datasource = mock(HomeSectionsDatasource::class.java)
        mapper = mock(HomeMapper::class.java)
        pagingSource = HomePagingSource(datasource, mapper)
    }

    @Test
    fun `load returns page on success`() = runTest {
        // Arrange
        val fakeResponse = SectionsResponse(
            sectionResponses = emptyList(),
            paginationResponse = PaginationResponse(
                totalPages = 1,
                nextPage = "/home_sections?page=2"
            )
        )

        val fakeMapped = listOf(
            HomeSection(
                name = "Test",
                type = SectionType.SQUARE,
                contentType = ContentType.PODCAST,
                order = 1,
                content = emptyList()
            )
        )

        // Stub datasource + mapper
        `when`(datasource.getHomeSections(1)).thenReturn(ResultWrapper.Success(fakeResponse))
        `when`(mapper.mapToHomeSections(fakeResponse)).thenReturn(fakeMapped)

        // Act
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 10,
                placeholdersEnabled = false
            )
        )

        // Assert
        val page = result as PagingSource.LoadResult.Page
        assertThat(page.data).isEqualTo(fakeMapped)
        assertThat(page.prevKey).isNull()
        assertThat(page.nextKey).isNull()
    }

    @Test
    fun `load returns error`() = runTest {
        `when`(datasource.getHomeSections(1)).thenReturn(ResultWrapper.Error(NetworkError.ServerError()))

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(key = 1, loadSize = 10, placeholdersEnabled = false)
        )

        assertThat(result).isInstanceOf(PagingSource.LoadResult.Error::class.java)
    }
}
