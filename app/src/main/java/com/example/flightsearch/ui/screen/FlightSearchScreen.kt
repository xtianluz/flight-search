package com.example.flightsearch.ui.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.R
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.LocalData
import com.example.flightsearch.navigation.NavigationDestination
import com.example.flightsearch.ui.AppViewModelProvider
import com.example.flightsearch.ui.composable.UserSelectedFavoriteList
import kotlinx.coroutines.launch

object SearchDestination: NavigationDestination{
    override val route = "search_screen"
    override val titleRes = R.string.app_name
}

@Composable
fun SearchScreen(
    navigateToFlightDetails: (String) -> Unit,
    viewModel: FlightSearchViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = viewModel.uiState
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchTextField(
            value = viewModel.userInput,
            onValueChange = {viewModel.updateUserInput(it)}
        )
        when(uiState){
            is UiState.Result -> SearchResultList(
                airportList = uiState.searchResult,
                onItemClick = navigateToFlightDetails
            )
            is UiState.Favorite -> UserSelectedFavoriteList(
                favoriteList = uiState.favoriteList,
                onClick = {
                    scope.launch {
                        viewModel.removeFromFavoriteWithQuery(
                            departureCode = it.departure_code,
                            destinationCode = it.destination_code,
                        )
                        if(viewModel.removedOrNot){
                            Toast.makeText(context,"Removed Successfully", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(context,"Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )
            else -> Text("Search")
        }
    }
}

@Composable
private fun SearchResultList(
    airportList: List<Airport>,
    onItemClick: (String) -> Unit
){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(9.dp)
    ){
        items(
            items = airportList,
            key = {it.id}
        ){item ->
            SearchResult(
                airport = item,
                onItemClick = { onItemClick(item.iata_code) }
            )
            Divider()
        }
    }
}

@Composable
private fun SearchResult(
    airport: Airport,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
            .padding(
                start = 12.dp,
                top = 3.dp,
                end = 12.dp
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 6.dp),
            text = airport.iata_code,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .weight(5f),
            text = airport.name,
            maxLines = 1
        )
    }
}

@Composable
private fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            shape = CircleShape
        )
    }
}


