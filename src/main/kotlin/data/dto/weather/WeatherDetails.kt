package org.example.data.dto.weather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDetails(
    @SerialName("time") val time: String? = null,
    @SerialName("interval") val interval: Int? = null,
    @SerialName("temperature_2m") val temperature: Double? = null,
    @SerialName("relative_humidity_2m") val humidity: Int? = null,
    @SerialName("wind_speed_10m") val windSpeed: Double? = null,
    @SerialName("rain") val rain: Double? = null,
    @SerialName("is_day") val isDay: Int? = null
)
