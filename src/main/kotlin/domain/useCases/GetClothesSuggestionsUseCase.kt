package org.example.domain.useCases

import org.example.domain.entities.*
import org.example.domain.repositories.LocationRepository
import org.example.domain.repositories.WeatherRepository
import org.example.domain.useCases.strategies.ClothesSuggestionStrategy

class GetClothesSuggestionsUseCase(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository,
    private val clothesSuggestionStrategies: List<ClothesSuggestionStrategy>
) {
    suspend fun execute(city: String, country: String): List<ClothItem> {
        val location = locationRepository.getLocationByCityAndCountry(city,country)
        val weather = weatherRepository.getWeatherDataByLocation(location)
        return clothesSuggestionStrategies.flatMap { it.suggest(weather) }
    }
}