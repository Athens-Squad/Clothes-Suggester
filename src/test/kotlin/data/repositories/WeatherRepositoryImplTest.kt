package data.repositories

import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.example.data.datasource.WeatherDataSource
import org.example.data.repositories.WeatherRepositoryImpl
import org.example.domain.entities.Location
import org.example.domain.entities.WeatherData
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WeatherRepositoryImplTest {

    private lateinit var dataSource: WeatherDataSource
    private lateinit var repository: WeatherRepositoryImpl

    @BeforeEach
    fun setUp() {
        dataSource = mockk()
        repository = WeatherRepositoryImpl(dataSource)
    }

    @Test
    fun `getWeatherDataByLocation returns weather data successfully`() = runTest {
        // Given
        val location = Location(latitude = 30.0444, longitude = 31.2357, city = "Cairo", country = "Egypt")
        val expected = WeatherData(
            temperature = 25.0,
            windSpeed = 12.0,
            rain = 0.0,
            humidity = 60.0,
            isDay = true
        )

        coEvery { dataSource.getWeatherData(location) } returns expected

        // When
        val result = repository.getWeatherDataByLocation(location)

        // Then
        assertThat(result).isEqualTo(expected)
        coVerify(exactly = 1) { dataSource.getWeatherData(location) }
    }

    @Test
    fun `getWeatherDataByLocation throws exception when data source fails`() = runTest {
        // Given
        val location = Location(latitude = 0.0, longitude = 0.0, city = "Nowhere", country = "Unknown")
        coEvery { dataSource.getWeatherData(location) } throws Exception("API Error")

        // When / Then
        try {
            repository.getWeatherDataByLocation(location)
            assert(false) // Should not reach this line
        } catch (e: Exception) {
            assertThat(e.message).isEqualTo("API Error")
        }

        coVerify(exactly = 1) { dataSource.getWeatherData(location) }
    }
}
