package com.mousa.thamnyahapp.presentation.screen.main

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mousa.thamnyahapp.R
import com.mousa.thamnyahapp.presentation.composables.sections.navigation.BottomNavItem
import com.mousa.thamnyahapp.presentation.composables.sections.navigation.NavigationHost
import com.mousa.thamnyahapp.presentation.theme.Teal
import com.mousa.thamnyahapp.presentation.theme.ThamnyahAppTheme
import com.mousa.thamnyahapp.presentation.theme.White
import com.mousa.thamnyahapp.presentation.theme.arabicFontFamily
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScreenContent()
        }
    }
}

@Composable
fun ScreenContent() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    ThamnyahAppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomNavigationBar(
                    currentRoute = currentRoute,
                    onItemClick = { item ->
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }) { innerPadding ->
            BackgroundWithGradient()
            Column(Modifier.padding(innerPadding)) {
                HeaderLogo()
                NavigationHost(navController)
            }
        }
    }
}


@Composable
fun BackgroundWithGradient(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Image(painter = painterResource(id = R.drawable.top_gradient), contentDescription = null)
        Spacer(modifier = Modifier.weight(1f))
        Image(painter = painterResource(id = R.drawable.bottom_gradient), contentDescription = null)
    }
}

@Composable
fun HeaderLogo(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_number),
            contentDescription = null,
            modifier = modifier
                .size(48.dp)
                .clip(shape = MaterialTheme.shapes.medium)
        )

        Text(
            text = "ألثمانية",
            fontFamily = arabicFontFamily,
            fontSize = 32.sp,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun BottomNavigationBar(
    currentRoute: String?,
    onItemClick: (BottomNavItem) -> Unit
) {
    NavigationBar(containerColor = Color.Transparent) {
        BottomNavItem.items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { onItemClick(item) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = { Text(item.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Teal,
                    unselectedIconColor = White,
                    selectedTextColor = Teal,
                    unselectedTextColor = White,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainScreenPreview() {
    ScreenContent()
}