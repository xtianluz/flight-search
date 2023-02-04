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

    val flightList: List<Flights> = listOf(
        Flights(
            arrivalCode = "SVO",
            arrivalName = "Arrival 1",
            departureCode = "FCO",
            departureName = "Leonardo"
        ),
        Flights(
            arrivalCode = "MAD",
            arrivalName = "Arrival 2",
            departureCode = "FCO",
            departureName = "Leonardo"
        ),
        Flights(
            arrivalCode = "TES",
            arrivalName = "Arrival 3",
            departureCode = "FCO",
            departureName = "Leonardo"
        ),
        Flights(
            arrivalCode = "LOC",
            arrivalName = "Arrival 4",
            departureCode = "FCO",
            departureName = "Leonardo"
        )
    )
}