package org.example.data.Mapper

import org.example.data.dto.LocationDto
import org.example.domain.Expception.LocationExceptions
import org.example.domain.entities.Location

class LocationMapper {

    fun mapDtoTOLocationCoordinate(locationsDetails:LocationDto) : Location{
        val locationsDetails = locationsDetails.locationsDetails ?:throw LocationExceptions.NoLocationFoundException()
        val FirstLocation = locationsDetails.firstOrNull() ?:throw LocationExceptions.NoLocationFoundException()
        val latitude = FirstLocation.latitude ?: throw LocationExceptions.NoLocationFoundException()
       val longitude =  FirstLocation.longitude  ?: throw LocationExceptions.NoLocationFoundException()
        return Location( latitude , longitude )
    }
}