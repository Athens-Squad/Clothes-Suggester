package domain.useCases.strategies

import com.google.common.truth.Truth.assertThat
import org.example.domain.entities.ClothItem
import org.example.domain.entities.ClothType
import org.example.domain.entities.ClothWeight
import org.example.domain.entities.WeatherData
import org.example.domain.useCases.strategies.RainClothesSuggestionStrategy
import kotlin.test.Test

class RainClothesSuggestionStrategyTest {


    private val strategy = RainClothesSuggestionStrategy()

    @Test
    fun `suggest() returns rain clothes when rain is higher`() {
        // given
        val weatherData = WeatherData(
            temperature = 15.0,
            windSpeed = 5.0,
            rain = 5.0,
            humidity = 70.0,
            isDay = true
        )

        val expected = listOf(
            ClothItem("Coat", ClothType.TOP, ClothWeight.LIGHT),
            ClothItem("Umbrella", ClothType.ACCESSORY, ClothWeight.MEDIUM),
            ClothItem("Gloves", ClothType.ACCESSORY, ClothWeight.HEAVY)
        )

        // when
        val result = strategy.suggest(weatherData)

        // then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `suggest() returns empty list when no rain`() {
        // given
        val weatherData = WeatherData(
            temperature = 25.0,
            windSpeed = 3.0,
            rain = 0.0,
            humidity = 50.0,
            isDay = true
        )

        // when
        val result = strategy.suggest(weatherData)

        // then
        assertThat(result).isEmpty()
    }
}