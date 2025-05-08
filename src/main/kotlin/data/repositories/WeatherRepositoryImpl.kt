package org.example.data.repositories

import org.example.data.datasource.WeatherDataSource
import org.example.domain.entities.Location
import org.example.domain.entities.WeatherData
import org.example.domain.repositories.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherDataSource: WeatherDataSource
): WeatherRepository {
    override suspend fun getWeatherDataByLocation(location: Location): WeatherData {
        TODO("Not yet implemented")
    }
}