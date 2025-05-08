package data.datasource.remote

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

class WeatherDataSourceImplTest {

    private lateinit var weatherDataSource: WeatherDataSource
    private lateinit var repository: WeatherRepositoryImpl

    @BeforeEach
    fun setUp() {
        weatherDataSource = mockk()
        repository = WeatherRepositoryImpl(weatherDataSource)
    }

    @Test
    fun `getWeatherDataByLocation returns weather data successfully`() = runTest {
        // Given
        val location = Location(latitude = 30.0444, longitude =  31.2357, city = "Cairo", country =  "Egypt")
        val expected = WeatherData(temperature = 22.5, windSpeed = 100.5, rain = 20.0, humidity = 5.0, isDay = true)

        coEvery { weatherDataSource.getWeatherData(location) } returns expected

        // When
        val result = repository.getWeatherDataByLocation(location)

        // Then
        assertThat(result).isEqualTo(expected)
        coVerify(exactly = 1) { weatherDataSource.getWeatherData(location) }
    }

    @Test
    fun `getWeatherDataByLocation throws exception when data source fails`() = runTest {
        // Given
        val location = Location(0.0, 0.0, "UnKnown", "Unknown")
        coEvery { weatherDataSource.getWeatherData(location) } throws Exception("Network error")

        // When / Then
        try {
            repository.getWeatherDataByLocation(location)
            assert(false)
        } catch (e: Exception) {
            assertThat(e.message).isEqualTo("Network error")
        }

        coVerify(exactly = 1) { weatherDataSource.getWeatherData(location) }
    }
}
