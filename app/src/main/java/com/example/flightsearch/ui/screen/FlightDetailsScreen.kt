package com.example.flightsearch.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.R
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.Favorite
import com.example.flightsearch.navigation.NavigationDestination
import com.example.flightsearch.ui.AppViewModelProvider
import com.example.flightsearch.ui.composable.FavoriteWidget
import com.example.flightsearch.ui.composable.FlightTopAppBar
import kotlinx.coroutines.launch

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
    val context = LocalContext.current
    val selectedDeparture = viewModel.selectedDeparture
    val selectedArrival = viewModel.selectedArrivals
    val scope = rememberCoroutineScope()
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
                addToFavoriteWithQuery = {scope.launch{
                        viewModel.addToFavoriteWithQuery(
                            departureCode = it.departure_code,
                            destinationCode = it.destination_code,
                            id = it.id
                        )
                        if(viewModel.alreadyExist){
                            Toast.makeText(context,"Favorite already exist", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(context,"Added Successfully", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
            )
    }
}

@Composable
private fun SelectedFlightList(
    modifier: Modifier = Modifier,
    destinationList: List<Airport>,
    selectedDeparture: Airport,
    addToFavoriteWithQuery: (Favorite) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(9.dp)
    ){
        items(
            items = destinationList,
            key = {item -> item.id}
            ){item ->
            FavoriteWidget(
                departureCode = selectedDeparture.iata_code,
                departureName = selectedDeparture.name,
                arrivalCode = item.iata_code,
                arrivalName = item.name,
                onClick = {
                    addToFavoriteWithQuery(
                        Favorite(
                            id = 0,
                            departure_code = selectedDeparture.iata_code,
                            destination_code = item.iata_code
                        )
                    )
                },
                imageVector = Icons.Filled.AddCircle
            )
            Divider(modifier = Modifier.padding(top = 9.dp))
        }
    }
}





