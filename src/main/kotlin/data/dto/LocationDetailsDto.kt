package org.example.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDetailsDto(
    @SerialName("id"           ) var id          : Int?    = null,
    @SerialName("name"         ) var name        : String? = null,
    @SerialName("latitude"     ) var latitude    : Double? = null,
    @SerialName("longitude"    ) var longitude   : Double? = null,
    @SerialName("elevation"    ) var elevation   : Int?    = null,
    @SerialName("feature_code" ) var featureCode : String? = null,
    @SerialName("country_code" ) var countryCode : String? = null,
    @SerialName("timezone"     ) var timezone    : String? = null,
    @SerialName("population"   ) var population  : Int?    = null,
    @SerialName("country_id"   ) var countryId   : Int?    = null,
    @SerialName("country"      ) var country     : String? = null

)
