package com.example.flightsearch.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    val selectedDeparture = viewModel.selectedDeparture
    val selectedArrival = viewModel.selectedArrivals
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
                destinationList = selectedArrival,
                modifier = modifier.padding(innerPadding),
                selectedDeparture = selectedDeparture,
                addRemoveToFavorite = {}
            )
    }
}

@Composable
fun SelectedFlightList(
    modifier: Modifier = Modifier,
    destinationList: List<Airport>,
    selectedDeparture: Airport,
    addRemoveToFavorite: () -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(9.dp)
    ){
        items(
            items = destinationList,
            key = {item -> item.id}
            ){item ->
            ToFavouriteWidget(
                departureCode = selectedDeparture.iata_code,
                departureName = selectedDeparture.name,
                arrivalCode = item.iata_code,
                arrivalName = item.name,
                addRemoveToFavorite = addRemoveToFavorite,
            )
            Divider(modifier = Modifier.padding(top = 9.dp))
        }
    }
}

@Composable
fun ToFavouriteWidget(
    departureCode: String,
    departureName: String,
    arrivalCode: String,
    arrivalName: String,
    addRemoveToFavorite: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = modifier.weight(6f)
        ) {
            FlightComponent(
                flightCode = departureCode,
                flightName = departureName,
                flightLabel = stringResource(R.string.depart)
            )
            FlightComponent(
                flightCode = arrivalCode,
                flightName = arrivalName,
                flightLabel = stringResource(R.string.arrive)
            )
        }
        ToFavouriteButton(
            addRemoveToFavorite = addRemoveToFavorite,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ToFavouriteButton(
    modifier: Modifier = Modifier,
    addRemoveToFavorite: () -> Unit,
    isFavourite: Boolean = false,
){
    val starTint = if(isFavourite){
        Color.Cyan
    }else{ Color.LightGray }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ){
        IconButton(onClick = addRemoveToFavorite) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Star Button",
                tint = starTint,
                modifier = Modifier.size(26.dp)
            )
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
        modifier = modifier.height(46.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = flightLabel,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 6.dp)
            )
            Text(
                text = flightCode
            )
        }
        Text(
            text = flightName,
            modifier = Modifier
                .padding(start = 6.dp, end = 6.dp)
                .fillMaxWidth()
                .weight(5f),
            maxLines = 1,
            textAlign = TextAlign.Start
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewComposable(){
    FlightSearchTheme {
        ToFavouriteWidget(
            departureCode = "asd",
            departureName = "asd",
            arrivalCode = "asdf",
            arrivalName = "asdf",
            addRemoveToFavorite = { /*TODO*/ },
        )
    }
}

