package com.mousa.thamnyahapp.usecase

import androidx.paging.PagingData
import com.mousa.thamnyahapp.domain.model.*
import com.mousa.thamnyahapp.domain.repository.HomeRepository
import com.mousa.thamnyahapp.domain.usecase.GetHomeSectionsUseCase
import com.mousa.thamnyahapp.utils.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import com.google.common.truth.Truth.assertThat

@ExperimentalCoroutinesApi
class GetHomeSectionsUseCaseTest {

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    private lateinit var repository: HomeRepository
    private lateinit var useCase: GetHomeSectionsUseCase

    @Before
    fun setup() {
        repository = mock(HomeRepository::class.java)
        useCase = GetHomeSectionsUseCase(repository)
    }

    @Test
    fun `invoke returns paging data`() = runTest {
        val fakePagingData = PagingData.from(
            listOf(HomeSection("Test", SectionType.SQUARE, ContentType.PODCAST, 1, emptyList()))
        )
        `when`(repository.getHomeSectionsStream()).thenReturn(flowOf(fakePagingData))

        val result = useCase().first()
        assertThat(result).isNotNull()
    }
}
