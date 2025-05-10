package org.example.data.datasource

import org.example.data.dto.weather.WeatherDataDto
import org.example.domain.entities.Location

interface WeatherDataSource {
    suspend fun getWeatherData(location: Location): WeatherDataDto
}