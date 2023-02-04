package com.example.flightsearch.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
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
import com.example.flightsearch.ui.theme.FlightSearchTheme

@Composable
fun FlightDetails() {

}

@Composable
fun FlightPairsList() {

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
            )
    ) {
        Column(
        modifier = Modifier
            .weight(1f)
            .padding(start = 6.dp),
            
        ) {
            Text(
                modifier = Modifier,
//                    .fillMaxWidth(),
                text = stringResource(flightLabel),
                textAlign = TextAlign.Center,
                fontSize = 12.sp
            )
            Text(
                modifier = Modifier,
//                    .fillMaxWidth(),
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
    FlightSearchTheme() {
        FlightPair(
            flights = Flights(
                departureCode = "FCO",
                departureName = "Los Angeles",
                arrivalCode = "MAD",
                arrivalName = "Arnold"
            )
        )
    }
}