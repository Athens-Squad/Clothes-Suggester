package domain.useCases

import io.mockk.coEvery
import io.mockk.mockk
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.example.domain.entities.*
import org.example.domain.repositories.LocationRepository
import org.example.domain.repositories.WeatherRepository
import org.example.domain.useCases.GetClothesSuggestionsUseCase
import org.example.domain.useCases.strategies.ClothesSuggestionStrategy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GetClothesSuggestionsUseCaseTest {

    private val weatherRepository = mockk<WeatherRepository>()
    private val locationRepository = mockk<LocationRepository>()
    private val strategy = mockk<ClothesSuggestionStrategy>()

    private val useCase = GetClothesSuggestionsUseCase(
        weatherRepository,
        locationRepository,
        listOf(strategy)
    )


    @Test
    fun `execute() return  clothes suggestions based on weather`() = runTest {
        //given
        val location = Location(30.0444, 31.2357, "Cairo", "Egypt")

        val weather = WeatherData(
            temperature = 38.0,
            windSpeed = 10.0,
            rain = 0.0,
            humidity = 20.0,
            isDay = true
        )
        val expectedClothes = listOf(
            ClothItem("T-Shirt", ClothType.TOP, ClothWeight.LIGHT),
            ClothItem("Shorts", ClothType.BOTTOM, ClothWeight.LIGHT),
            ClothItem("Sunglasses", ClothType.ACCESSORY, ClothWeight.LIGHT),
            ClothItem("Cap or Hat", ClothType.ACCESSORY, ClothWeight.LIGHT)
        )


        coEvery { locationRepository.getLocationByCityAndCountry(location.city, location.country) } returns location
        coEvery { weatherRepository.getWeatherDataByLocation(location) } returns weather
        coEvery { strategy.suggest(weather) } returns expectedClothes

        // when
        val result = useCase.execute(location.city, location.country)
        //then
        assertThat(result).isEqualTo(expectedClothes)
    }

    @Test
    fun `execute() throw exception when weather api failed`() = runTest {
        //given
        val location = Location(30.0444, 31.2357, "Cairo", "Egypt")
        coEvery { weatherRepository.getWeatherDataByLocation(location) } throws RuntimeException("Network error")
        coEvery { weatherRepository.getWeatherDataByLocation(location) } throws RuntimeException("Network error")


        //when
        val exception = assertThrows<RuntimeException> {
            runBlocking {
                useCase.execute(location.city, location.country)
            }
        }
        //then
        assertThat("Network error").isEqualTo(exception.message)
    }

    @Test
    fun `execute() throw exception when location not found`() = runTest {
        //given
        val location = Location(0.0, 0.0, "Unknown", "Nowhere")
        coEvery { weatherRepository.getWeatherDataByLocation(location) } throws IllegalArgumentException("Location not found")
        //when
        val exception = assertThrows<IllegalArgumentException> {
            useCase.execute(location.city, location.country)
        }
        //then
        assertThat("Location not found").isEqualTo(exception.message)
    }
}