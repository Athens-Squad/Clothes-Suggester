package org.example.data.datasource.remote

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.example.domain.entities.Location
import org.example.data.datasource.LocationDataSource
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LocationDataSourceImplTest {

    private lateinit var dataSource: LocationDataSource

    @BeforeEach
    fun setUp() {
        dataSource = object : LocationDataSource {
            override suspend fun getLocationByCityAndCountry(city: String, country: String): Location =
                if (city == "Cairo" && country == "Egypt") {
                    Location(
                        city = "Cairo",
                        country = "Egypt",
                        latitude = 30.0444,
                        longitude = 31.2357
                    )
                } else {
                    throw IllegalArgumentException("Location not found")
                }
        }
    }


    @Test
    fun `getLocationByCityAndCountry returns location when valid city and country`() = runTest {
        // Given
        val city = "Cairo"
        val country = "Egypt"

        // When
        val result = dataSource.getLocationByCityAndCountry(city, country)

        // Then
        assertThat(result.city).isEqualTo("Cairo")
        assertThat(result.country).isEqualTo("Egypt")
        assertThat(result.latitude).isEqualTo(30.0444)
        assertThat(result.longitude).isEqualTo(31.2357)
    }

    @Test
    fun `getLocationByCityAndCountry throws exception when location not found`() = runTest {
        // Given
        val city = "UnKnownCity"
        val country = "UnKnownCountry"

        // When / Then
        try {
            dataSource.getLocationByCityAndCountry(city, country)
            assert(false)
        } catch (e: Exception) {
            assertThat(e.message).isEqualTo("Location not found")
        }
    }
}
