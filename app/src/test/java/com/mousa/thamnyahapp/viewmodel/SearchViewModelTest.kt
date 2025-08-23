package com.mousa.thamnyahapp.viewmodel

import com.google.common.truth.Truth.assertThat
import com.mousa.thamnyahapp.data.remote.utils.NetworkError
import com.mousa.thamnyahapp.data.remote.utils.ResultWrapper
import com.mousa.thamnyahapp.domain.model.ContentType
import com.mousa.thamnyahapp.domain.model.HomeSection
import com.mousa.thamnyahapp.domain.model.SectionType
import com.mousa.thamnyahapp.domain.usecase.SearchUseCase
import com.mousa.thamnyahapp.presentation.screen.search.SearchUiState
import com.mousa.thamnyahapp.presentation.screen.search.SearchViewModel
import com.mousa.thamnyahapp.utils.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    private lateinit var searchUseCase: SearchUseCase
    @OptIn(FlowPreview::class)
    private lateinit var viewModel: SearchViewModel

    @OptIn(FlowPreview::class)
    @Before
    fun setup() {
        searchUseCase = mock(SearchUseCase::class.java)
        viewModel = SearchViewModel(searchUseCase)
    }

    @OptIn(FlowPreview::class)
    @Test
    fun `search updates state to success`() = runTest {
        val fakeSections = listOf(HomeSection("Result", SectionType.SQUARE, ContentType.PODCAST, 1, emptyList()))
        `when`(searchUseCase("test")).thenReturn(flowOf(ResultWrapper.Success(fakeSections)))

        viewModel.onQueryChange("test")
        advanceUntilIdle()

        val state = viewModel.state.value
        assertThat(state).isInstanceOf(SearchUiState.Success::class.java)
    }

    @OptIn(FlowPreview::class)
    @Test
    fun `search updates state to error`() = runTest {
        `when`(searchUseCase("fail")).thenReturn(flowOf(ResultWrapper.Error(NetworkError.General())))

        viewModel.onQueryChange("fail")
        advanceUntilIdle()

        val state = viewModel.state.value
        assertThat(state).isInstanceOf(SearchUiState.Error::class.java)
    }
}
