package org.example.data.mapper

import org.example.data.dto.weather.WeatherDataDto
import org.example.domain.entities.WeatherData


fun WeatherDataDto.mapToWeatherData() : WeatherData {
    return WeatherData(
        temperature = this.weatherDetails?.temperature ?: throw NoSuchElementException(),
        windSpeed = this.weatherDetails.windSpeed ?: throw NoSuchElementException() ,
        rain = this.weatherDetails.rain  ?: throw NoSuchElementException() ,
        humidity =  this.weatherDetails.humidity?.toDouble() ?: throw NoSuchElementException(),
        isDay = this.weatherDetails.isDay?.equals(1) ?: throw NoSuchElementException(),
    )
}