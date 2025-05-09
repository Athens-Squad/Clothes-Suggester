package domain.useCases.strategies

import com.google.common.truth.Truth.assertThat
import domain.useCases.getWeatherData
import org.example.domain.entities.ClothItem
import org.example.domain.entities.ClothType
import org.example.domain.entities.ClothWeight
import org.example.domain.entities.WeatherData
import org.example.domain.useCases.strategies.WindClothesSuggestionStrategy
import kotlin.test.Test

class WindClothesSuggestionStrategyTest {

    private val strategy = WindClothesSuggestionStrategy()

    @Test
    fun `suggest() returns Windbreaker when wind speed is higher`() {
        //given
        val weather = WeatherData(
            temperature = 20.0,
            windSpeed = 20.0,
            rain = 0.0,
            humidity = 50.0,
            isDay = true
        )

        val expected = listOf(
            ClothItem("Windbreaker", ClothType.TOP, ClothWeight.MEDIUM)
        )
        //when

        val result = strategy.suggest(weather)
        //then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `suggest() returns empty list when wind speed is below or equal to normal wind speed`() {
         //given

        val weatherData = getWeatherData(20.0,10.0,0.0,50.0,true)

        //when
        val result = strategy.suggest(weatherData)
         //then
        assertThat(result).isEmpty()
    }


}