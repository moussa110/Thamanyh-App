package com.mousa.thamnyahapp.presentation.composables.sections.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    data object Home : BottomNavItem(Routes.HOME, "Home", Icons.Outlined.Home)
    data object Search : BottomNavItem(Routes.SEARCH, "Search", Icons.Outlined.Search)
    companion object { val items = listOf(Home, Search) }
}