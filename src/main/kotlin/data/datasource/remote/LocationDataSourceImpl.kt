package org.example.data.datasource.remote

import org.example.data.datasource.LocationDataSource
import org.example.domain.entities.Location

class LocationDataSourceImpl: LocationDataSource {
    override suspend fun getLocationByCityAndCountry(city: String, country: String): Location {
        TODO("Not yet implemented")
    }
}