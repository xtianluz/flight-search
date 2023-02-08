package com.example.flightsearch.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flightsearch.R
import com.example.flightsearch.data.Flights
import com.example.flightsearch.data.LocalData
import com.example.flightsearch.navigation.NavigationDestination
import com.example.flightsearch.ui.composable.FlightTopAppBar
import com.example.flightsearch.ui.theme.FlightSearchTheme

object FlightDetailDestination: NavigationDestination{
    override val route = "flight_details"
    override val titleRes = R.string.flight_details
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/${itemIdArg}"
}

@Composable
fun FlightDetailsScreen(
    onNavigateUp: () -> Unit
) {
    Scaffold(
        topBar = {
            FlightTopAppBar(
                title = stringResource(FlightDetailDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ){ innerPadding ->
        FlightPairList(
            modifier = Modifier.padding(innerPadding)
        )

    }
}

@Composable
fun FlightPairList(
    flightList: List<Flights> = LocalData.flightList,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(9.dp)
    ){
        items(
            items = flightList,
            key = {item -> item.id}
            ){ item ->
            FlightPair(flights = item)
            Divider(modifier = Modifier.padding(top = 9.dp))
        }
    }
}

@Composable
fun FlightPair(flights: Flights) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(5f)
        ) {
            Flight(
                flightLabel = R.string.depart,
                flightCode = flights.departureCode,
                flightName = flights.departureName,
            )
            Flight(
                flightLabel = R.string.arrive,
                flightCode = flights.arrivalCode,
                flightName = flights.arrivalName,
            )
        }
        IconButton(
            onClick = {},
            modifier = Modifier
                .weight(1f)
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = stringResource(R.string.favourite),
                tint = Color.Cyan,
                modifier = Modifier
                    .size(36.dp)
            )
        }
    }
}

@Composable
fun Flight(
    flightLabel: Int,
    flightCode: String,
    flightName: String
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 12.dp,
                top = 6.dp
            )
    ) {
        Column(
        modifier = Modifier
            .weight(1f)
            .padding(start = 6.dp),
            
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(flightLabel),
                textAlign = TextAlign.Center,
                fontSize = 12.sp
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = flightCode,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            text = flightName,
            modifier = Modifier
                .weight(5f)
                .padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 3.dp
                )
        )

    }
}

////////////////////
@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun FlightDetailsPreview() {
    FlightSearchTheme {
//        FlightPair(
//            flights = Flights(
//                departureCode = "FCO",
//                departureName = "Los Angeles",
//                arrivalCode = "MAD",
//                arrivalName = "Arnold"
//            )
//        )
        FlightPairList()
    }
}
