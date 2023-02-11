package com.example.flightsearch.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.navigation.NavigationDestination
import com.example.flightsearch.ui.AppViewModelProvider

object MockDestination: NavigationDestination{
    override val route = "mock_screen"
    override val titleRes = "Mock Screen"
}

@Composable
fun MockScreen(
){
    Column {
        Text(
            text = "placeholder"
        )
    }
}