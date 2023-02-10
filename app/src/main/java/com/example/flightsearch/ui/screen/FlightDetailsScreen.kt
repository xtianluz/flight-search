package com.example.flightsearch.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.R
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.Flights
import com.example.flightsearch.data.LocalData
import com.example.flightsearch.navigation.NavigationDestination
import com.example.flightsearch.ui.AppViewModelProvider
import com.example.flightsearch.ui.composable.FlightTopAppBar
import com.example.flightsearch.ui.theme.FlightSearchTheme
import kotlinx.coroutines.flow.emptyFlow

object FlightDetailDestination: NavigationDestination{
    override val route = "flight_details"
    override val titleRes = R.string.flight_details
    const val itemIdArg = "itemIdArg"
    val routeWithArgs = "${route}/${itemIdArg}"
}

@Composable
fun FlightDetailsScreen(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit,
    viewModel: FlightSearchViewModel = viewModel(factory = AppViewModelProvider.Factory),

) {
//    val flightInfo = viewModel.getAnItem().collectAsState(emptyFlow<Airport>()).value
//    val flightInfo = viewModel.getCode().collectAsState(Airport(0,"","",0))

    Scaffold(
        topBar = {
            FlightTopAppBar(
                title = stringResource(FlightDetailDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ){ innerPadding ->

//        Flight(
//            flightLabel = R.string.flight_label,
//            flightCode = flightInfo.iata_code,
//            flightName = flightInfo.name,
//            modifier = modifier.padding(innerPadding)
//        )
        Text(
            text = viewModel.itemState.collectAsState(Airport(0,"","",1)).value.iata_code,
            modifier = modifier.padding(innerPadding)
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
    flightName: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = modifier
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
