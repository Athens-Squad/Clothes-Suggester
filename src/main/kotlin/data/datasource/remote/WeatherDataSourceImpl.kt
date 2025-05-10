package org.example.data.datasource.remote

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import org.example.data.datasource.WeatherDataSource
import org.example.data.dto.weather.WeatherDataDto
import org.example.domain.entities.Location

class WeatherDataSourceImpl: WeatherDataSource {
    override suspend fun getWeatherData(location: Location): WeatherDataDto {
        val client = HttpClient(CIO)
        val response: HttpResponse = client.submitForm(
            url = "https://api.open-meteo.com/v1/forecast",
            formParameters = parameters {
                append("latitude",location.latitude.toString())
                append("longitude",location.longitude.toString())
                append("current","temperature_2m,relative_humidity_2m,wind_speed_10m,rain,is_day")
            }
        )
        return Json.decodeFromString<WeatherDataDto>(response.bodyAsText())
    }
}