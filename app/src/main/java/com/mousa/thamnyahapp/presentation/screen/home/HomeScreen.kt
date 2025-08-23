package com.mousa.thamnyahapp.presentation.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mousa.thamnyahapp.domain.model.HomeSection
import com.mousa.thamnyahapp.presentation.composables.componnents.AppendErrorItem
import com.mousa.thamnyahapp.presentation.composables.componnents.ErrorScreen
import com.mousa.thamnyahapp.presentation.composables.componnents.LoadingMoreItem
import com.mousa.thamnyahapp.presentation.composables.componnents.LoadingScreen
import com.mousa.thamnyahapp.presentation.composables.sections.SectionScreen

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
            SectionScreen(section)
        }

        if (lazyPagingItems.loadState.append is LoadState.Loading) {
            item { LoadingMoreItem() }
        }

        if (lazyPagingItems.loadState.append is LoadState.Error) {
            item { AppendErrorItem(onRetry = onRetry) }
        }
    }
}
