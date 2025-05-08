package domain.useCases

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.example.domain.entities.Location
import org.example.domain.repositories.LocationRepository
import org.example.domain.useCases.GetLocationByCityAndCountry
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class GetLocationByCityAndCountryTest{

  private val locationRepository = mockk<LocationRepository>()
  private val useCase = GetLocationByCityAndCountry(locationRepository)

  @Test
  fun `execute() return location for valid city and country`(){
   runBlocking {
    //given

    val city = "Cairo"
    val country = "Egypt"
    val expected = Location(30.0444, 31.2357, city, country)

    coEvery { locationRepository.getLocationByCityAndCountry(city, country) } returns expected

   // when
   val result = useCase.execute(city, country)
    //then
   assertThat(result).isEqualTo(expected)


  }
  }


 @Test
 fun `execute()  throw exception for not found country`(){
   runBlocking {

    //given
    val city = "Atlantis"
    val country = "UnknownLand"

    coEvery { locationRepository.getLocationByCityAndCountry(city, country) } throws IllegalArgumentException("City not found")

    //when

    val exception= assertThrows<IllegalArgumentException> {
       runBlocking { useCase.execute(city, country) }
    }

    //then
     assertThat("country not found").isEqualTo(exception)

   }




 }

 @Test
 fun `execute()  throw exception when api failed`() {

  //given
  runBlocking {

   val city = "Cairo"
   val country = "Egypt"
   coEvery { locationRepository.getLocationByCityAndCountry(city, country) } throws RuntimeException("Network failure")

   //when
   val exception = assertThrows<RuntimeException> {
    runBlocking {
     useCase.execute(city, country)
    }
  }

   //then
   assertThat("Network error").isEqualTo(exception)



  }
 }

}





