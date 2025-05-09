package org.example.data.repositories

import org.example.data.mapper.LocationMapper
import org.example.data.datasource.LocationDataSource
import org.example.data.dto.LocationDto
import org.example.domain.entities.Location
import org.example.domain.repositories.LocationRepository

class LocationRepositoryImpl(
    private val locationDataSource: LocationDataSource ,
    private val locationMapper : LocationMapper
): LocationRepository {
    override suspend fun getLocationByCityAndCountry(city: String, country: String): Location {
        val locationDetails = locationDataSource.getLocationByCityAndCountry(city ,country)
       return  locationMapper.mapDtoTOLocationCoordinate(locationDetails)
    }
}