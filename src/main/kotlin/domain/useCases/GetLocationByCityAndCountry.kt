package org.example.domain.useCases

import org.example.domain.entities.Location

class GetLocationByCityAndCountry {
    suspend operator fun invoke(city: String, country: String): Location {
        return Location(
            0.0,
            0.0,
            "",
            ""
        )
    }
}