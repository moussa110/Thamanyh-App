package com.mousa.thamnyahapp.presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mousa.thamnyahapp.domain.model.HomeSection
import com.mousa.thamnyahapp.domain.model.SectionType
import com.mousa.thamnyahapp.presentation.composables.AppendErrorItem
import com.mousa.thamnyahapp.presentation.composables.BigSquareSection
import com.mousa.thamnyahapp.presentation.composables.ErrorScreen
import com.mousa.thamnyahapp.presentation.composables.LoadingMoreItem
import com.mousa.thamnyahapp.presentation.composables.LoadingScreen
import com.mousa.thamnyahapp.presentation.composables.QueueSection
import com.mousa.thamnyahapp.presentation.composables.SectionTitle
import com.mousa.thamnyahapp.presentation.composables.SquareSection
import com.mousa.thamnyahapp.presentation.composables.TwoLinesGridSection
import com.mousa.thamnyahapp.presentation.utils.FakeSectionsProvider.sections

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val lazyPagingItems = viewModel.homeSections.collectAsLazyPagingItems()

    when (val state = lazyPagingItems.loadState.refresh) {
        is LoadState.Loading -> LoadingScreen()
        is LoadState.Error -> ErrorScreen(
            message = state.error.localizedMessage ?: "Unknown error",
            onRetry = { lazyPagingItems.retry() }
        )

        else -> {
            HomeContent(
                lazyPagingItems = lazyPagingItems,
                onRetry = { lazyPagingItems.retry() }
            )
        }
    }
}

@Composable
fun HomeContent(
    lazyPagingItems: LazyPagingItems<HomeSection>,
    onRetry: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(lazyPagingItems.itemCount) { index ->
            val section = lazyPagingItems[index] ?: return@items

            Column {
                SectionTitle(title = section.name)

                when (section.type) {
                    SectionType.SQUARE -> SquareSection(section)
                    SectionType.BIG_SQUARE -> BigSquareSection(section)
                    SectionType.QUEUE -> QueueSection(section)
                    SectionType.TWO_LINES_GRID -> TwoLinesGridSection(section)
                    else -> {QueueSection(section)}
                }
            }
        }

        if (lazyPagingItems.loadState.append is LoadState.Loading) {
            item { LoadingMoreItem() }
        }

        if (lazyPagingItems.loadState.append is LoadState.Error) {
            item { AppendErrorItem(onRetry = onRetry) }
        }
    }
}
