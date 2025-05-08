package org.example.data.datasource

import org.example.domain.entities.Location

interface LocationDataSource {
    suspend fun getLocationByCityAndCountry(city: String, country: String): Location
}