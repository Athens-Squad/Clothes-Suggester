package org.example.data.repositories

import org.example.data.datasource.LocationDataSource
import org.example.domain.entities.Location
import org.example.domain.repositories.LocationRepository

class LocationRepositoryImpl(
    private val locationDataSource: LocationDataSource
): LocationRepository {
    override suspend fun getLocationByCityAndCountry(city: String, country: String): Location {
        TODO("Not yet implemented")
    }
}