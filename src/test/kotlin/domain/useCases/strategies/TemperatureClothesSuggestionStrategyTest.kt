package domain.useCases.strategies

import com.google.common.truth.Truth.assertThat
import domain.useCases.getWeatherData
import org.example.domain.entities.ClothItem
import org.example.domain.entities.ClothType
import org.example.domain.entities.ClothWeight
import org.example.domain.entities.WeatherData
import org.example.domain.useCases.strategies.TemperatureClothesSuggestionStrategy
import kotlin.test.Test

class TemperatureClothesSuggestionStrategyTest {


    private val strategy = TemperatureClothesSuggestionStrategy()

    @Test
    fun `suggest() returns hot weather clothes when temperature is very hot`() {

        //given
          val weatherData = getWeatherData(35.0,5.0,0.0,30.0,true)


        val expected = listOf(
            ClothItem("T-Shirt", ClothType.TOP, ClothWeight.LIGHT),
            ClothItem("Shorts", ClothType.BOTTOM, ClothWeight.LIGHT),
            ClothItem("Sunglasses", ClothType.ACCESSORY, ClothWeight.LIGHT),
            ClothItem("Cap or Hat", ClothType.ACCESSORY, ClothWeight.LIGHT)
        )

        //when
        val result = strategy.suggest(weatherData)
        //then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `suggest() returns warm weather clothes when temperature is warm but less than or equal to hot`() {

       //given
        val weather = WeatherData(
            temperature = 25.0,
            windSpeed = 3.0,
            rain = 0.0,
            humidity = 40.0,
            isDay = true
        )

        val expected = listOf(
            ClothItem("Long-sleeve Shirt", ClothType.TOP, ClothWeight.MEDIUM),
            ClothItem("Pants", ClothType.BOTTOM, ClothWeight.MEDIUM)
        )
        //when

        val result = strategy.suggest(weather)
          //then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `suggest() returns cool weather clothes when temperature is above cool but less than or equal to warm`() {

      //given
        val weather = WeatherData(
            temperature = 15.0,
            windSpeed = 2.0,
            rain = 0.0,
            humidity = 50.0,
            isDay = true
        )

        val expected = listOf(
            ClothItem("Jacket", ClothType.TOP, ClothWeight.HEAVY),
            ClothItem("Warm pants", ClothType.BOTTOM, ClothWeight.HEAVY)
        )
        //when

        val result = strategy.suggest(weather)
        //then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `suggest() returns cold weather clothes when temperature is at or below cool`() {

       //given
        val weather = WeatherData(
            temperature = 5.0,
            windSpeed = 4.0,
            rain = 0.0,
            humidity = 60.0,
            isDay = false
        )

        val expected = listOf(
            ClothItem("Coat", ClothType.TOP, ClothWeight.VERY_HEAVY),
            ClothItem("Warm pants", ClothType.BOTTOM, ClothWeight.HEAVY)
        )
        //when
        val result = strategy.suggest(weather)
         //then
        assertThat(result).isEqualTo(expected)
    }
}