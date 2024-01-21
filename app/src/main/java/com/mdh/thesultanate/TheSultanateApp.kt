package com.mdh.thesultanate

import BottomBar
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.mdh.thesultanate.ui.theme.TheSultanateTheme
import com.mdh.thesultanate.ui.navigation.Screen
import com.mdh.thesultanate.ui.screen.detail.DetailScreen
import com.mdh.thesultanate.ui.screen.home.HomeScreen
import com.mdh.thesultanate.ui.screen.profile.ProfileScreen

@Composable
fun TheSultanateApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.Detail.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(onClick = { detailId ->
                    navController.navigate(Screen.Detail.createRoute(detailId))
                })
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("detailId") { type = NavType.LongType }),
            ) {
                val id = it.arguments?.getLong("detailId") ?: -1L
                DetailScreen(
                    detailId = id,
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun BottomPreview() {
    TheSultanateTheme {
        TheSultanateApp()
    }
}
