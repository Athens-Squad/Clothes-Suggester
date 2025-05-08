package org.example.domain.useCases.strategies

import org.example.domain.entities.ClothItem
import org.example.domain.entities.ClothType
import org.example.domain.entities.ClothWeight
import org.example.domain.entities.WeatherData

class RainClothesSuggestionStrategy : ClothesSuggestionStrategy {
    override fun suggest(weatherData: WeatherData): List<ClothItem> {
        return if (weatherData.rain > RAIN_THRESHOLD) {
            listOf(
                ClothItem("Coat", ClothType.TOP, ClothWeight.LIGHT),
                ClothItem("Umbrella", ClothType.ACCESSORY, ClothWeight.MEDIUM),
                ClothItem("Gloves", ClothType.ACCESSORY, ClothWeight.HEAVY)
            )
        } else emptyList()
    }

    companion object {
        private const val RAIN_THRESHOLD = 0.0
    }
}
