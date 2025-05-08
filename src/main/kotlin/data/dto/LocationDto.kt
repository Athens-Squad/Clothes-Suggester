package org.example.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    @SerialName("results")
    val locationsDetails: List<LocationDetailsDto>? = null,
    @SerialName("generationtime_ms")
    val generationTimeMs: Double?
)