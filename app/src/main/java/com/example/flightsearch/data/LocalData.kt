package com.example.flightsearch.data

object LocalData{
    val airportList: List<Airport> = listOf(
        Airport(
            id = 1,
            name = "Sheremetyevo - A.S. Pushkin international airport",
            iataCode = "SVO",
            passenger = 3
        ),
        Airport(
            id = 2,
            name = "Vienna International Airport",
            iataCode = "VIE",
            passenger = 6
        ),
        Airport(
            id = 3,
            name = "Adolfo Suárez Madrid–Barajas Airport",
            iataCode = "MAD",
            passenger = 6
        ),
        Airport(
            id = 4,
            name = "Leonardo da Vinci International Airport",
            iataCode = "FCO",
            passenger = 6
        ),
    )
}