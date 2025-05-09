package org.example.data.datasource.remote

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json
import org.example.data.datasource.LocationDataSource
import org.example.data.dto.LocationDto

class LocationDataSourceImpl : LocationDataSource {
    override suspend fun getLocationByCityAndCountry(city: String, country: String): LocationDto {
        val client = HttpClient(CIO)
        val response = client.get{
            url("https://geocoding-api.open-meteo.com/v1/search?name=$city&country=$country")

        }

        return Json.decodeFromString<LocationDto>(response.bodyAsText())
    }
}


