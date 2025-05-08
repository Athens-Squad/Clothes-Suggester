package domain.useCases

import io.mockk.coEvery
import io.mockk.mockk
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.example.domain.entities.*
import org.example.domain.repositories.WeatherRepository
import org.example.domain.useCases.GetClothesSuggestionsUseCase
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GetClothesSuggestionsUseCaseTest {
    private val weatherRepository = mockk<WeatherRepository>()
    private val useCase = GetClothesSuggestionsUseCase(weatherRepository)

    @Test
    fun `execute() return  clothes suggestions based on weather`() {
        runBlocking {

            //given
            val location = Location(30.0444, 31.2357, "Cairo", "Egypt")

            val weather = WeatherData(
                temperature = 38.0,
                windSpeed = 10.0,
                rain = 0.0,
                humidity = 20.0,
                isDay = true
            )
            val expected = listOf(
                ClothItem("T-Shirt", ClothType.TOP, ClothWeight.LIGHT),
                ClothItem("Shorts", ClothType.BOTTOM, ClothWeight.LIGHT),
                ClothItem("Sunglasses", ClothType.TOP, ClothWeight.VERY_LIGHT),
                ClothItem("Cap or Hat", ClothType.TOP, ClothWeight.LIGHT)
            )
            coEvery { weatherRepository.getWeatherDataByLocation(location) } returns weather

            // when
            val result = useCase.execute(location)
            //then
            assertThat(result).isEqualTo(expected)


        }

    }

    @Test
    fun `execute() throw exception when weather api failed`() {
     //given
        runBlocking {
            val location = Location(30.0444, 31.2357, "Cairo", "Egypt")

            coEvery { weatherRepository.getWeatherDataByLocation(location) } throws RuntimeException("Network error")
          //when
            val exception = assertThrows<RuntimeException> {
                runBlocking {
                    useCase.execute(location)
                }
            }


         //then
            assertThat("Network error").isEqualTo(exception.message)

        }
    }

 @Test
 fun `execute() throw exception when location not found`() {
    //given
  val location = Location(0.0, 0.0, "Unknown", "Nowhere")

  coEvery { weatherRepository.getWeatherDataByLocation(location) } throws IllegalArgumentException("Location not found")
   //when
  val exception = assertThrows<IllegalArgumentException> {
   runBlocking {
    useCase.execute(location)
   }
  }
  //then

  assertThat("Location not found").isEqualTo(exception.message)
 }




 }

