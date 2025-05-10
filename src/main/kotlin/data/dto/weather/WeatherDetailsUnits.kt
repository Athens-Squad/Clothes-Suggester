package org.example.data.dto.weather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDetailsUnits(
    @SerialName("time") val time: String? = null,
    @SerialName("interval") val interval: String? = null,
    @SerialName("temperature_2m") val temperature2m: String? = null,
    @SerialName("relative_humidity_2m") val relativeHumidity2m: String? = null,
    @SerialName("wind_speed_10m") val windSpeed10m: String? = null,
    @SerialName("rain") val rain: String? = null,
    @SerialName("is_day") val isDay: String? = null
)
