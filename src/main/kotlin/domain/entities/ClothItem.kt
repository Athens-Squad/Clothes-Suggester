package org.example.domain.entities

data class ClothItem(
    val name: String,
    val type: ClothType,
    val weight: ClothWeight
)

enum class ClothWeight {
    VERY_LIGHT, LIGHT, MEDIUM, HEAVY, VERY_HEAVY
}

enum class ClothType {
    TOP, BOTTOM
}
