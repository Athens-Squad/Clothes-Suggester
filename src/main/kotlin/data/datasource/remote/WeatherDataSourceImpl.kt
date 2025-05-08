package org.example.data.datasource.remote

import org.example.data.datasource.WeatherDataSource
import org.example.domain.entities.Location
import org.example.domain.entities.WeatherData

class WeatherDataSourceImpl: WeatherDataSource {
    override suspend fun getWeatherData(location: Location): WeatherData {
        TODO("Not yet implemented")
    }
}