package org.example.domain.useCases

import org.example.domain.entities.*
import org.example.domain.repositories.WeatherRepository
import org.example.domain.useCases.strategies.ClothesSuggestionStrategy

class GetClothesSuggestionsUseCase(
    private val weatherRepository: WeatherRepository,
    private val clothesSuggestionStrategies: List<ClothesSuggestionStrategy>
) {
    suspend fun execute(location: Location): List<ClothItem> {
        val weather = weatherRepository.getWeatherDataByLocation(location)
        return clothesSuggestionStrategies.flatMap { it.suggest(weather) }
    }
}