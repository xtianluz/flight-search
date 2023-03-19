package com.example.flightsearch.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.flightsearch.ui.screen.FlightDetailDestination
import com.example.flightsearch.ui.screen.FlightDetailsScreen
import com.example.flightsearch.ui.screen.SearchDestination
import com.example.flightsearch.ui.screen.SearchScreen

interface NavigationDestination {
    val route: String
    val titleRes: Any
}

@Composable
fun FlightNavHost(
    navController: NavHostController,
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
                    navController.navigate(SearchDestination.route)
                }
            )
        }
    }
}