package com.example.flightsearch.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.R
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.Favourites
import com.example.flightsearch.data.LocalData
import com.example.flightsearch.navigation.NavigationDestination
import com.example.flightsearch.ui.AppViewModelProvider
import com.example.flightsearch.ui.theme.FlightSearchTheme

object SearchDestination: NavigationDestination{
    override val route = "search_screen"
    override val titleRes = R.string.app_name
}

@Composable
fun SearchScreen(
    navigateToFlightDetails: (Airport) -> Unit = {},
    viewModel: FlightSearchViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val allItems by viewModel.getAllItems().collectAsState(emptyList())

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchTextField(
            value = "",
            onValueChange = {}
        )
        SearchResultList(
            airportList = allItems,
            onItemClick = navigateToFlightDetails
        )
    }
}

@Composable
fun SearchTextField(
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

@Composable
fun SearchResultList(
    airportList: List<Airport>,
    onItemClick: (Airport) -> Unit
){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(9.dp)
    ){
        items(
            items = airportList,
            key = {airportList -> airportList.id}
        ){item ->
            SearchResult(
                airport = item,
                onItemClick = onItemClick
            )
            Divider()
        }
    }
}

@Composable
fun SearchResult(
    airport: Airport,
    onItemClick: (Airport) -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(airport) }
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
            text = airport.iataCode,
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
fun Favourites(
    favourites: List<Favourites>
){



}


//////////////////////////

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun SearchFieldPreview(){
    FlightSearchTheme() {
        Column(

        ) {
//            SearchTextField(
//                value = "Placeholder",
//                onValueChange = {})
//            SearchResult(
//                flightCode = "FCO",
//                flightName = "Sheremetyevo - A.S. Pushkin international airport"
//            )
//            SearchResult(
//                flightCode = "FCO",
//                flightName = "Los Angeles"
//            )
//            SearchResult(
//                flightCode = "FCO",
//                flightName = "Los Angeles"
//            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview(){
    FlightSearchTheme {
//        SearchScreen()
    }
}
