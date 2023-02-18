package com.example.flightsearch.data

object LocalData{
    val airportList: List<Airport> = listOf(
        Airport(
            id = 1,
            name = "Sheremetyevo - A.S. Pushkin international airport",
            iata_code = "SVO",
            passengers = 3
        ),
        Airport(
            id = 2,
            name = "Vienna International Airport",
            iata_code = "VIE",
            passengers = 6
        ),
        Airport(
            id = 5,
            name = "Adolfo Suárez Madrid–Barajas Airport",
            iata_code = "MAD",
            passengers = 6
        ),
        Airport(
            id = 4,
            name = "Leonardo da Vinci International Airport",
            iata_code = "FCO",
            passengers = 6
        ),
    )

    val singleAirport: Airport = Airport(
        0,"FOO","FCO", 0
    )

    val selectedList: List<SelectedFavorite> = listOf(
        SelectedFavorite(
            id = 0,
            departureCode = "BOO",
            departureName = "FOO",
            destinationCode = "BOO",
            destinationName = "FOO"
        ),
    )
    val singleFavorite: Favorite = Favorite(
        id = 0,
        departure_code = "FOO",
        destination_code = "FOO"
    )
    val selectedFavorite: SelectedFavorite = SelectedFavorite(
        id = 0,
        destinationCode = "FOO",
        departureCode = "FOO",
        destinationName = "FOO",
        departureName = "FOO"
    )
    val nullFavorite: Favorite = Favorite(id=1, destination_code = "", departure_code = "")
}