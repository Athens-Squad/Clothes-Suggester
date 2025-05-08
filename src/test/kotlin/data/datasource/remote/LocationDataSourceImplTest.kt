package data.datasource.remote

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.example.data.datasource.LocationDataSource
import org.example.data.datasource.remote.LocationDataSourceImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LocationDataSourceImplTest {

    private lateinit var dataSource: LocationDataSource

    @BeforeEach
    fun setUp() {
        dataSource = LocationDataSourceImpl()
    }


    @Test
    fun `getLocationByCityAndCountry returns location when valid city`() = runTest {
        // Given
        val city = "Cairo"
        val country = "Egypt"

        // When
        val result = dataSource.getLocationByCityAndCountry(city, country)

        // Then
        assertThat(result.city).isEqualTo("Cairo")
    }

    @Test
    fun `getLocationByCityAndCountry returns location when valid country`() = runTest {
        // Given
        val city = "Cairo"
        val country = "Egypt"

        // When
        val result = dataSource.getLocationByCityAndCountry(city, country)

        // Then
        assertThat(result.country).isEqualTo("Egypt")
    }

    @Test
    fun `getLocationByCityAndCountry returns location when valid longitude`() = runTest {
        // Given
        val city = "Cairo"
        val country = "Egypt"


        // When
        val result = dataSource.getLocationByCityAndCountry(city, country)

        // Then
        assertThat(result.longitude).isEqualTo(31.2357)
    }

    @Test
    fun `getLocationByCityAndCountry returns location when valid latitude`() = runTest {
        // Given
        val city = "Cairo"
        val country = "Egypt"
        // When
        val result = dataSource.getLocationByCityAndCountry(city, country)
        // Then
        assertThat(result.latitude).isEqualTo(30.0444)
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
