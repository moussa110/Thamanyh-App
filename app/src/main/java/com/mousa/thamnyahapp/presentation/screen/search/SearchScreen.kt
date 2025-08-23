package com.mousa.thamnyahapp.presentation.screen.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mousa.thamnyahapp.presentation.composables.componnents.LoadingScreen
import com.mousa.thamnyahapp.presentation.composables.sections.SectionScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val query by viewModel.query.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = query,

            onValueChange = viewModel::onQueryChange,
            modifier = Modifier.fillMaxWidth().testTag("SearchBar"),
            placeholder = { Text("Search") }
        )

        Spacer(Modifier.height(16.dp))

        when (state) {
            is SearchUiState.Loading -> {
                LoadingScreen(modifier = Modifier.testTag("Loading"))
            }

            is SearchUiState.Success -> {
                val sections = (state as SearchUiState.Success).sections
                LazyColumn {
                    items(sections.size) { index ->
                        val section = sections[index]
                        SectionScreen(section = section)
                    }
                }
            }

            is SearchUiState.Error -> {
                Column(
                    Modifier.fillMaxSize().testTag("ErrorView"),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val msg = (state as SearchUiState.Error).message
                    Text("Error: $msg")
                }

            }

            is SearchUiState.Empty -> {
                Text("No results found", modifier = Modifier.testTag("EmptyView"))
            }
        }
    }
}