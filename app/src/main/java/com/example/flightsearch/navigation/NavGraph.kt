package com.example.flightsearch.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.flightsearch.ui.screen.SearchDestination
import com.example.flightsearch.ui.screen.SearchScreen

interface NavigationDestination {
    val route: String
    val titleRes: Int
}

@Composable
fun FlightNavHost(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = SearchDestination.route,
    ){
        composable(route = SearchDestination.route){
            SearchScreen()
        }
    }
}