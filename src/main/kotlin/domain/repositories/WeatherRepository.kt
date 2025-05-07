package org.example.domain.repositories

import org.example.domain.entities.Location
import org.example.domain.entities.WeatherData

interface WeatherRepository {
    suspend fun getWeatherDataByLocation(location: Location): WeatherData
}