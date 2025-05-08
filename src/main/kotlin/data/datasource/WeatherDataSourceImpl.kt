package org.example.data.datasource

import org.example.domain.entities.Location
import org.example.domain.entities.WeatherData

class WeatherDataSourceImpl: WeatherDataSource {
    override suspend fun getWeatherData(location: Location): WeatherData {
        TODO("Not yet implemented")
    }
}