package com.example.flightsearch.data

object LocalData{
    val airportList: List<Airport> = listOf(
        Airport(
            id = 1,
            name = "Sheremetyevo - A.S. Pushkin international airport",
            iataCode = "SVO",
            passengers = 3
        ),
        Airport(
            id = 2,
            name = "Vienna International Airport",
            iataCode = "VIE",
            passengers = 6
        ),
        Airport(
            id = 5,
            name = "Adolfo Suárez Madrid–Barajas Airport",
            iataCode = "MAD",
            passengers = 6
        ),
        Airport(
            id = 4,
            name = "Leonardo da Vinci International Airport",
            iataCode = "FCO",
            passengers = 6
        ),
    )

    val flightList: List<Flights> = listOf(
        Flights(
            arrivalCode = "SVO",
            arrivalName = "Arrival 1",
            departureCode = "FCO",
            departureName = "Leonardo",
            id = 1
        ),
        Flights(
            arrivalCode = "MAD",
            arrivalName = "Arrival 2",
            departureCode = "FCO",
            departureName = "Leonardo",
            id = 2
        ),
        Flights(
            arrivalCode = "TES",
            arrivalName = "Arrival 3",
            departureCode = "FCO",
            departureName = "Leonardo",
            id = 3
        ),
        Flights(
            arrivalCode = "LOC",
            arrivalName = "Arrival 4",
            departureCode = "FCO",
            departureName = "Leonardo",
            id = 4
        )
    )
}