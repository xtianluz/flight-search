package com.example.flightsearch.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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

object FlightDetailDestination: NavigationDestination{
    override val route = "flight_details"
    override val titleRes = R.string.flight_details
    const val itemIdArg = "itemIdArg"
    val routeWithArgs = "${route}/{$itemIdArg}"
}

@Composable
fun FlightDetailsScreen(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit,
    viewModel: FlightDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory),

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
            SelectedFlightList(
                destinationList = LocalData.airportList,
                modifier = modifier.padding(innerPadding),
                selectedDeparture = LocalData.singleAirport
            )
//        Text(
//            text = viewModel.itemCode,
//            modifier = modifier.padding(innerPadding)
//        )
    }
}

@Composable
fun SelectedFlightList(
    modifier: Modifier = Modifier,
    destinationList: List<Airport>,
    selectedDeparture: Airport
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(9.dp)
    ){
        items(
            items = destinationList,
            key = {item -> item.id}
            ){item ->
            FlightComponent(
                flightCode = selectedDeparture.iata_code,
                flightName = selectedDeparture.name,
                flightLabel = stringResource(R.string.depart)
            )
            FlightComponent(
                flightCode = item.iata_code,
                flightName = item.name,
                flightLabel = stringResource(R.string.arrive)
            )
            Divider(modifier = Modifier.padding(top = 9.dp))
        }
    }
}

@Composable
fun FlightComponent(
    modifier: Modifier = Modifier,
    flightCode: String,
    flightName: String,
    flightLabel: String
){
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = modifier.height(36.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                ,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = flightLabel,
                fontSize = 12.sp
            )
            Text(
                text = flightCode
            )
        }
        Text(
            text = flightName,
            modifier = Modifier
                .weight(6f)
                .padding(end = 6.dp),
            maxLines = 1,
            textAlign = TextAlign.Start
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewComposable(){
    FlightSearchTheme {
        SelectedFlightList(
            selectedDeparture = LocalData.singleAirport,
            destinationList = LocalData.airportList
        )
    }
}

