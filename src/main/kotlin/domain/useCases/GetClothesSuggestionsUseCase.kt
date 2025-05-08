package org.example.domain.useCases

import org.example.domain.entities.ClothItem
import org.example.domain.entities.Location
import org.example.domain.repositories.WeatherRepository

class GetClothesSuggestionsUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend fun execute(location: Location): List<ClothItem> {
        return emptyList()
    }
}