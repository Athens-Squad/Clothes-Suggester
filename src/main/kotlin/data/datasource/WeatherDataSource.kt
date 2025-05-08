package org.example.data.datasource

import org.example.domain.entities.Location
import org.example.domain.entities.WeatherData

interface WeatherDataSource {
    suspend fun getWeatherData(location: Location): WeatherData
}