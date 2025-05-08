package org.example.domain.useCases.strategies

import org.example.domain.entities.ClothItem
import org.example.domain.entities.ClothType
import org.example.domain.entities.ClothWeight
import org.example.domain.entities.WeatherData

class WindClothesSuggestionStrategy : ClothesSuggestionStrategy {
    override fun suggest(weatherData: WeatherData): List<ClothItem> {

        return if (weatherData.windSpeed > WIND_THRESHOLD) {
            listOf(ClothItem("Windbreaker", ClothType.TOP, ClothWeight.MEDIUM))
        } else emptyList()
    }

    companion object {
        private const val WIND_THRESHOLD = 15.0

    }
}