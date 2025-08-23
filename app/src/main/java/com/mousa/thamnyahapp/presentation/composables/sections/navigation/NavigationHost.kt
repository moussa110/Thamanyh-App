package com.mousa.thamnyahapp.presentation.composables.sections.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mousa.thamnyahapp.presentation.screen.home.HomeScreen
import com.mousa.thamnyahapp.presentation.screen.home.HomeViewModel
import com.mousa.thamnyahapp.presentation.screen.search.SearchScreen
import com.mousa.thamnyahapp.presentation.screen.search.SearchViewModel

@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME,
        modifier = modifier,
    ) {
        composable(Routes.HOME) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(viewModel = viewModel)
        }
        composable(Routes.SEARCH) {
            val viewModel: SearchViewModel = hiltViewModel()
            SearchScreen(viewModel)
        }
    }
}