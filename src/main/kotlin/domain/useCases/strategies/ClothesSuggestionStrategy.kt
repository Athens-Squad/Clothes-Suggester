package org.example.domain.useCases.strategies

import org.example.domain.entities.ClothItem
import org.example.domain.entities.WeatherData

interface ClothesSuggestionStrategy {
    fun suggest (weatherData: WeatherData) : List<ClothItem>
}