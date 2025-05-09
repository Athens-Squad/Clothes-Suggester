package domain.useCases

import org.example.domain.entities.WeatherData

fun getWeatherData(  temperature: Double=0.0,
                     windSpeed: Double=0.0,
                    rain: Double=0.0,
                    humidity: Double=0.0,
                     isDay: Boolean=true)= WeatherData(
    temperature = temperature,
    windSpeed =windSpeed,
    rain = rain,
    humidity = humidity,
    isDay = isDay
)