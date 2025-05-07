package org.example.domain.entities

data class ClothItem(
    val name: String,
    val type: ClothType,
    val weight: ClothWeight,
    val attribute: WeatherAppropriateClothAttribute
)

enum class ClothWeight {
    LIGHT, MEDIUM, HEAVY
}

enum class ClothType {
    TOP, BOTTOM
}

enum class WeatherAppropriateClothAttribute {
    WIND_APPROPRIATE, RAIN_APPROPRIATE
}