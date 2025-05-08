package org.example.data.Mapper

import org.example.data.dto.LocationDto
import org.example.domain.Expception.LocationExecption
import org.example.domain.entities.Location

class LocationMapper {

    fun mapDtoTOLocationCoordinate(locationsDetails:LocationDto) : Location{
        val locationsDetails = locationsDetails.locationsDetails ?:throw LocationExecption.NoLocationFoundException()
        val FirstLocation = locationsDetails.firstOrNull() ?:throw LocationExecption.NoLocationFoundException()
        val latitude = FirstLocation.latitude ?: throw LocationExecption.NoLocationFoundException()
       val longitude =  FirstLocation.longitude  ?: throw LocationExecption.NoLocationFoundException()
        return Location( latitude , longitude )
    }
}