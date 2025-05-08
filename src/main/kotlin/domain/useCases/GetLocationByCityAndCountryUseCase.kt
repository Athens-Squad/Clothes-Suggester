package org.example.domain.useCases

import org.example.domain.entities.Location
import org.example.domain.repositories.LocationRepository

class GetLocationByCityAndCountryUseCase(
    private val locationRepository: LocationRepository
) {
    suspend fun execute(city: String, country: String): Location {
        return locationRepository.getLocationByCityAndCountry(city,country)
    }
}