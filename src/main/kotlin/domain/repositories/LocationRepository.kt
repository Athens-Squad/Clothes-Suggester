package org.example.domain.repositories

import org.example.domain.entities.Location

interface LocationRepository {
    suspend fun getLocationByCityAndCountry(city: String, country: String): Location
}