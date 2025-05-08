package data.repositories

import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.example.data.datasource.LocationDataSource
import org.example.data.repositories.LocationRepositoryImpl
import org.example.domain.entities.Location
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LocationRepositoryImplTest {

    private lateinit var dataSource: LocationDataSource
    private lateinit var repository: LocationRepositoryImpl

    @BeforeEach
    fun setUp() {
        dataSource = mockk()
        repository = LocationRepositoryImpl(dataSource)
    }

    @Test
    fun `getLocationByCityAndCountry returns location successfully`() = runTest {
        // Given
        val city = "Cairo"
        val country = "Egypt"
        val expected = Location(latitude = 30.0444, longitude = 31.2357, city, country)

        coEvery { dataSource.getLocationByCityAndCountry(city, country) } returns expected

        // When
        val result = repository.getLocationByCityAndCountry(city, country)

        // Then
        assertThat(result).isEqualTo(expected)
        coVerify(exactly = 1) { dataSource.getLocationByCityAndCountry(city, country) }
    }

    @Test
    fun `getLocationByCityAndCountry throws exception when data source fails`() = runTest {
        // Given
        val city = "Unknown"
        val country = "Nowhere"
        coEvery { dataSource.getLocationByCityAndCountry(city, country) } throws Exception("Not found")

        // When / Then
        try {
            repository.getLocationByCityAndCountry(city, country)
            assert(false)
        } catch (e: Exception) {
            assertThat(e.message).isEqualTo("Not found")
        }

        coVerify(exactly = 1) { dataSource.getLocationByCityAndCountry(city, country) }
    }
}
