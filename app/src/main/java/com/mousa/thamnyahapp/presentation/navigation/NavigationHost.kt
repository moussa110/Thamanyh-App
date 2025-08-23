package com.mousa.thamnyahapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mousa.thamnyahapp.presentation.screen.home.HomeScreen
import com.mousa.thamnyahapp.presentation.screen.home.HomeViewModel
import com.mousa.thamnyahapp.presentation.screen.search.SearchScreen

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
            SearchScreen()
        }
    }
}