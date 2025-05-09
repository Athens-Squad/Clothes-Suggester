package domain.useCases.strategies

import com.google.common.truth.Truth.assertThat
import domain.useCases.getWeatherData
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
        val weatherData = getWeatherData(15.0,5.0,5.0,70.0,true)

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
        val weatherData = getWeatherData(25.0,3.0,0.0,50.0,true)

        // when
        val result = strategy.suggest(weatherData)

        // then
        assertThat(result).isEmpty()
    }
}