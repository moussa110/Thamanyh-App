package com.mousa.thamnyahapp.repository

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.ListUpdateCallback
import com.google.common.truth.Truth.assertThat
import com.mousa.thamnyahapp.data.mappers.HomeMapper
import com.mousa.thamnyahapp.data.remote.datasource.HomeSectionsDatasource
import com.mousa.thamnyahapp.data.remote.response.home.SectionsResponse
import com.mousa.thamnyahapp.data.remote.utils.ResultWrapper
import com.mousa.thamnyahapp.data.repository.HomeRepositoryImpl
import com.mousa.thamnyahapp.domain.model.ContentType
import com.mousa.thamnyahapp.domain.model.HomeSection
import com.mousa.thamnyahapp.domain.model.SectionType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@OptIn(ExperimentalCoroutinesApi::class)
class HomeRepositoryTest {

    private val datasource = mock(HomeSectionsDatasource::class.java)
    private val mapper = mock(HomeMapper::class.java)
    private val repository = HomeRepositoryImpl(datasource, mapper)

    private val noopListUpdateCallback = object : ListUpdateCallback {
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
        override fun onMoved(fromPosition: Int, toPosition: Int) {}
        override fun onChanged(position: Int, count: Int, payload: Any?) {}
    }

    @Test
    fun `getHomeSectionsStream returns data on success`() = runTest {
        // Arrange
        val fakeResponse = mock(SectionsResponse::class.java)
        val fakeMapped = listOf(
            HomeSection("Test", SectionType.SQUARE, ContentType.PODCAST, 1, emptyList())
        )

        `when`(datasource.getHomeSections(1)).thenReturn(ResultWrapper.Success(fakeResponse))
        `when`(mapper.mapToHomeSections(fakeResponse)).thenReturn(fakeMapped)

        val flow = repository.getHomeSectionsStream()

        val differ = AsyncPagingDataDiffer(
            diffCallback = HomeSectionDiffCallback(),
            updateCallback = noopListUpdateCallback,
            workerDispatcher = Dispatchers.Main
        )

        // Act
        flow.collect { pagingData: PagingData<HomeSection> ->
            differ.submitData(pagingData)
        }

        // Assert
        assertThat(differ.snapshot()).isNotEmpty()
        assertThat(differ.snapshot()[0]?.name).isEqualTo("Test")
    }
}
