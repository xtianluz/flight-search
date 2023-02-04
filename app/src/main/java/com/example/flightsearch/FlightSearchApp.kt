package com.example.flightsearch

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.flightsearch.navigation.FlightNavHost

@Composable
fun FlightSearchApp(navController: NavHostController = rememberNavController()){
    FlightNavHost(navController = navController)
}