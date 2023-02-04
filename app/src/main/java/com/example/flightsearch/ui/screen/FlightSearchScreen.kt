package com.example.flightsearch.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flightsearch.R
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.LocalData
import com.example.flightsearch.navigation.NavigationDestination
import com.example.flightsearch.ui.theme.FlightSearchTheme

object SearchDestination: NavigationDestination{
    override val route = "search_screen"
    override val titleRes = R.string.app_name
}

@Composable
fun SearchScreen() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchTextField(
            value = "",
            onValueChange = {}
        )
        SearchResultList(airportList = LocalData.airportList)
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
    airportList: List<Airport>
){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(9.dp)
    ){
        items(
            items = airportList,
            key = {airportList -> airportList.id}
        ){item ->
            SearchResult(
                flightCode = item.iataCode,
                flightName = item.name
            )
            Divider()
        }
    }
}

@Composable
fun SearchResult(
    flightCode: String,
    flightName: String
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
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
            text = flightCode,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .weight(5f),
            text = flightName,
            maxLines = 1
        )
    }
}


//////////////////////////

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun SearchFieldPreview(){
    FlightSearchTheme() {
        Column(

        ) {
            SearchTextField(
                value = "Placeholder",
                onValueChange = {})
            SearchResult(
                flightCode = "FCO",
                flightName = "Sheremetyevo - A.S. Pushkin international airport"
            )
            SearchResult(
                flightCode = "FCO",
                flightName = "Los Angeles"
            )
            SearchResult(
                flightCode = "FCO",
                flightName = "Los Angeles"
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview(){
    FlightSearchTheme {
        SearchScreen()
    }
}
