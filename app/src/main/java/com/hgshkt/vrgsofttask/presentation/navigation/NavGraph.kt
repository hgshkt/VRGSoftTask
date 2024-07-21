package com.hgshkt.vrgsofttask.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hgshkt.vrgsofttask.presentation.screens.image.ImageScreen
import com.hgshkt.vrgsofttask.presentation.screens.main.MainScreen

@Composable
fun NavGraph(
    controller: NavHostController
) {
    NavHost(navController = controller, startDestination = Screen.Main.route) {
        composable(route = Screen.Main.route) {
            MainScreen()
        }
        composable(
            route = Screen.Image.routeWithArg,
            arguments = listOf(
                navArgument(Screen.Image.argName) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            ImageScreen(backStackEntry.arguments?.getString(Screen.Image.argName))
        }
    }
}

sealed class Screen(open val route: String) {
    data object Main : Screen("Main")
    data object Image : Screen("Image") {
        val argName = "photoUrl"
        val routeWithArg: String = "$route/{$argName}"
    }
}