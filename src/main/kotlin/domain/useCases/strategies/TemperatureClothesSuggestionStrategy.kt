package org.example.domain.useCases.strategies

import org.example.domain.entities.ClothItem
import org.example.domain.entities.ClothType
import org.example.domain.entities.ClothWeight
import org.example.domain.entities.WeatherData

class TemperatureClothesSuggestionStrategy : ClothesSuggestionStrategy {
    override fun suggest(weatherData: WeatherData): List<ClothItem> {
        val temperature = weatherData.temperature
        return when {
            temperature > HOT_THRESHOLD -> listOf(
                ClothItem("T-Shirt", ClothType.TOP, ClothWeight.LIGHT),
                ClothItem("Shorts", ClothType.BOTTOM, ClothWeight.LIGHT),
                ClothItem("Sunglasses", ClothType.ACCESSORY, ClothWeight.LIGHT),
                ClothItem("Cap or Hat", ClothType.ACCESSORY, ClothWeight.LIGHT)
            )
            temperature > WARM_THRESHOLD -> listOf(
                ClothItem("Long-sleeve Shirt", ClothType.TOP, ClothWeight.MEDIUM),
                ClothItem("Pants", ClothType.BOTTOM, ClothWeight.MEDIUM)
            )
            temperature > COOL_THRESHOLD -> listOf(
                ClothItem("Jacket", ClothType.TOP, ClothWeight.HEAVY),
                ClothItem("Warm pants", ClothType.BOTTOM, ClothWeight.HEAVY)
            )
            else -> listOf(
                ClothItem("Coat", ClothType.TOP, ClothWeight.VERY_HEAVY),
                ClothItem("Warm pants", ClothType.BOTTOM, ClothWeight.HEAVY)
            )
        }
    }

    companion object {
        private const val HOT_THRESHOLD = 30.0
        private const val WARM_THRESHOLD = 20.0
        private const val COOL_THRESHOLD = 10.0
    }
}