package org.example.data.datasource

import org.example.data.dto.LocationDto

interface LocationDataSource {
    suspend fun getLocationByCityAndCountry(city: String, country: String): LocationDto
}