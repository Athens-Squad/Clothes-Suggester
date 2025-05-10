package org.example.data.dto.weather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDataDto(
    @SerialName("latitude") val latitude: Double? = null,
    @SerialName("longitude") val longitude: Double? = null,
    @SerialName("generationtime_ms") val generationTimeMs: Double? = null,
    @SerialName("utc_offset_seconds") val utcOffsetSeconds: Int? = null,
    @SerialName("timezone") val timezone: String? = null,
    @SerialName("timezone_abbreviation") val timezoneAbbreviation: String? = null,
    @SerialName("elevation") val elevation: Double? = null,
    @SerialName("current_units") val weatherDetailsUnits: WeatherDetailsUnits? = WeatherDetailsUnits(),
    @SerialName("current") val weatherDetails: WeatherDetails? = WeatherDetails()
)
