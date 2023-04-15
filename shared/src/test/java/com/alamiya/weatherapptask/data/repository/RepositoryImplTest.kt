package com.alamiya.weatherapptask.data.repository

import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alamiya.weatherapptask.R
import com.alamiya.weatherapptask.data.source.dto.CashEntity
import com.alamiya.weatherapptask.data.source.dto.City
import com.alamiya.weatherapptask.data.source.dto.WeatherSuccessResponse
import com.alamiya.weatherapptask.data.source.local.FakeLocalDataSource
import com.alamiya.weatherapptask.data.source.remote.FakeRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsNull
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [32])
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class RepositoryImplTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var repositoryImpl: RepositoryImpl
    private lateinit var cashEntity: CashEntity
    private lateinit var weatherSuccessResponse: WeatherSuccessResponse

    @Before
    fun prepareDependencies(){
        // Fake data source
        weatherSuccessResponse  = WeatherSuccessResponse(
            city = City(),
            cod = "200" ,
            list = listOf(),
        )

        cashEntity = CashEntity(content = weatherSuccessResponse, cityName = "London")

        // Initialize Repository
        repositoryImpl = RepositoryImpl(
            local = FakeLocalDataSource(cashEntity),
            remote = FakeRemoteDataSource(weatherSuccessResponse),
            context = ApplicationProvider.getApplicationContext()
        )
    }


    @Test
    fun `getCash retrieveData checkIfNotNull`() = mainCoroutineRule.runBlockingTest {
        // Given: insert cash entity to database
        repositoryImpl.insertCash(cashEntity)

        // When: get cash entity from database
        val result = repositoryImpl.getCash(cashEntity.cityName).first()

        // Then:  check if not null
        assertThat(result, IsNull.notNullValue())

    }

    @Test
    fun `insertCash insertFakeData CheckIfNotNull`() = mainCoroutineRule.runBlockingTest {
        // Given: fake cash entity
        // When: insert cash entity to database
        repositoryImpl.insertCash(cashEntity)
        // Then: get cash entity from database and check if not null
        val result = repositoryImpl.getCash(cashEntity.cityName).first()
        assertThat(result, IsNull.notNullValue())
    }

    @Test
    fun `getWeatherDetails callApi checkIfNotNull`() = mainCoroutineRule.runBlockingTest {
        // Given: City Name
        val name = "London"
        // When: call weather api data from repo
        val result = repositoryImpl.getWeatherDetails(cityName = name)
        // Then: check if retrieved data not null
        if (result.isSuccessful)
            assertThat(result,IsNull.notNullValue())
    }


    @Test
    fun `getCountries callApi checkIfNotNull`() = mainCoroutineRule.runBlockingTest {
        // Given: Nothing
        // When:
        val result = repositoryImpl.getCountries(R.raw.countries).first()
        // Then: check if retrieved data not null
        assertThat(result,IsNull.notNullValue())
    }
}