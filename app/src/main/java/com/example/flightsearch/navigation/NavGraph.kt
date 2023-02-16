package com.example.flightsearch.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.flightsearch.ui.AppViewModelProvider
import com.example.flightsearch.ui.screen.*

interface NavigationDestination {
    val route: String
    val titleRes: Any
}

@Composable
fun FlightNavHost(
    navController: NavHostController,
    viewModel: FlightSearchViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    NavHost(
        navController = navController,
        startDestination = SearchDestination.route,
    ) {

        composable(route = SearchDestination.route) {
            SearchScreen(
                navigateToFlightDetails = {
                    navController.navigate("${FlightDetailDestination.route}/$it")
                }
            )
        }
        composable(
            route = FlightDetailDestination.routeWithArgs,
            arguments = listOf(navArgument(FlightDetailDestination.itemIdArg) {
                type = NavType.StringType
            })
        ) {
            FlightDetailsScreen(
                onNavigateUp = {
                    navController.navigateUp()
                }
            )
        }
    }
}