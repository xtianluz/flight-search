package com.example.flightsearch.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.flightsearch.data.Airport
import com.example.flightsearch.ui.AppViewModelProvider
import com.example.flightsearch.ui.screen.*
import kotlinx.coroutines.flow.emptyFlow

interface NavigationDestination {
    val route: String
    val titleRes: Int
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
                navigateToFlightDetails = { flightId ->
                    navController.navigate("${FlightDetailDestination.route}/$flightId")
//                    navController.navigate(FlightDetailDestination.route)
                }
            )
        }
//        composable(
//            route = FlightDetailDestination.routeWithArgs,
//            arguments = listOf(navArgument(FlightDetailDestination.itemIdArg) {
//                type = NavType.StringType
//            })
//        ) {backStackEntry ->
//            val code = backStackEntry.arguments?.getString(FlightDetailDestination.itemIdArg)
//                ?: error("error")
//            val flightInfo = viewModel.getCode(code).collectAsState(emptyFlow<Airport>())
//            FlightDetailsScreen(flightInfo.toString())
//        }
//        composable(route = FlightDetailDestination.route){
//            FlightDetailsScreen(
//                onNavigateUp = { navController.navigateUp() }
//            )
//        }
        composable(
            route = FlightDetailDestination.routeWithArgs,
            arguments = listOf(navArgument(FlightDetailDestination.itemIdArg) {
                type = NavType.StringType
            })
        ){
                FlightDetailsScreen(onNavigateUp = { navController.navigateUp() })
        }
    }
}