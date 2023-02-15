package com.example.flightsearch.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flightsearch.R

@Composable
fun FavoriteWidget(
    departureCode: String,
    departureName: String,
    arrivalCode: String,
    arrivalName: String,
    onClick: () -> Unit,
    imageVector: ImageVector,
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
        AddRemoveButton(
            onClick = onClick,
            modifier = Modifier.weight(1f),
            imageVector = imageVector
        )
    }
}

@Composable
private fun AddRemoveButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    imageVector: ImageVector
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ){
        IconButton(onClick = onClick) {
            Icon(
                imageVector = imageVector,
                contentDescription = "Star Button",
                tint = Color.LightGray,
                modifier = Modifier.size(26.dp)
            )
        }
    }
}

@Composable
private fun FlightComponent(
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
                .padding(
                    start = 6.dp,
                    end = 6.dp
                )
                .fillMaxWidth()
                .weight(5f),
            maxLines = 1,
            textAlign = TextAlign.Start
        )
    }
}