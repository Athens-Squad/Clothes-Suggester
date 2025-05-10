package org.example.data.mapper

import org.example.data.dto.LocationDto
import org.example.domain.entities.Location
import org.example.domain.exception.NoLocationFoundException

class LocationMapper {

    fun mapDtoTOLocationCoordinate(locationsDetails: LocationDto): Location {
        val locationsData = locationsDetails.locationsDetails ?: throw NoLocationFoundException()
        val firstLocation = locationsData.firstOrNull() ?: throw NoLocationFoundException()
        val latitude = firstLocation.latitude ?: throw NoLocationFoundException()
        val longitude = firstLocation.longitude ?: throw NoLocationFoundException()
        return Location(latitude, longitude)
    }
}