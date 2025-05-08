package org.example.domain.useCases

import org.example.domain.entities.Location
import org.example.domain.repositories.LocationRepository

class GetLocationByCityAndCountry(
    private val locationRepository: LocationRepository
) {
    suspend fun execute(city: String, country: String): Location {
        return Location(
            0.0,
            0.0,
            "",
            ""
        )
    }
}