package com.egbuna.autocheck.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.egbuna.autocheck.screens.CarDetailRoute
import com.egbuna.autocheck.screens.HomeRoute

@Composable
fun NavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        autoCheckGraph(navController)
        detailGraph(navController)
    }
}

fun NavGraphBuilder.autoCheckGraph(navController: NavController) {
    composable(route = Screen.Home.route) {
        HomeRoute(navController)
    }
}

fun NavGraphBuilder.detailGraph(navController: NavController) {
    composable(route = Screen.Detail.route+ "/{carId}/{carName}") { backStackEntry ->
        CarDetailRoute(
            backStackEntry.arguments?.getString("carId").orEmpty(),
            backStackEntry.arguments?.getString("carName").orEmpty(),
            navController
        )
    }
}