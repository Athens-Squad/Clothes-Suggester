package org.example.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDetailsDto(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("latitude") val latitude: Double? = null,
    @SerialName("longitude") val longitude: Double? = null,
    @SerialName("elevation") val elevation: Int? = null,
    @SerialName("feature_code") val featureCode: String? = null,
    @SerialName("country_code") val countryCode: String? = null,
    @SerialName("timezone") val timezone: String? = null,
    @SerialName("population") val population: Int? = null,
    @SerialName("country_id") val countryId: Int? = null,
    @SerialName("country") val country: String? = null

)
