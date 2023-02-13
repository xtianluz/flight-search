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
    val singleAirport: Airport = Airport(
        99,"San Fransisco Airport","FCO", 369
    )
}