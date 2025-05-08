package org.example.data.datasource

import org.example.domain.entities.Location

class LocationDataSourceImpl: LocationDataSource {
    override suspend fun getLocationByCityAndCountry(city: String, country: String): Location {
        TODO("Not yet implemented")
    }
}