package org.example.domain.entities

data class WeatherData(
    val temperature: Double,
    val windSpeed: Double,
    val rain: Double,
    val humidity: Double,
    val isDay: Boolean
)
