package com.example.flightsearch.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flightsearch.data.Favorite
import com.example.flightsearch.data.LocalData
import com.example.flightsearch.data.SelectedFavorite
import com.example.flightsearch.ui.theme.FlightSearchTheme

@Composable
fun UserSelectedFavoriteList(
    favoriteList: List<SelectedFavorite>,
    onClick: (Favorite) -> Unit
){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(9.dp)
    ){
        items(
            items = favoriteList,
            key = {item -> item.id}
        ) { item ->
            FavoriteWidget(
                departureCode = item.departureCode,
                departureName = item.departureName,
                arrivalCode = item.destinationCode,
                arrivalName = item.destinationName,
                onClick = { onClick(
                    Favorite(
                        id = 0,
                        departure_code = item.departureCode,
                        destination_code = item.destinationCode
                    )
                ) },
                imageVector = Icons.Filled.Delete
            )
            Divider(modifier = Modifier.padding(top = 9.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListPreview(){
    FlightSearchTheme() {
        UserSelectedFavoriteList(
            favoriteList = LocalData.selectedList,
            onClick = { }
        )
    }
}