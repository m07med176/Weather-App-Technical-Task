package com.alamiya.weatherapptask.data.repository

import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alamiya.weatherapptask.data.source.dto.CashEntity
import com.alamiya.weatherapptask.data.source.dto.City
import com.alamiya.weatherapptask.data.source.dto.WeatherSuccessResponse
import com.alamiya.weatherapptask.data.source.local.FakeLocalDataSource
import com.alamiya.weatherapptask.data.source.remote.FakeRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.Matchers.`is`
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

    lateinit var repositoryImpl: RepositoryImpl
    lateinit var cashEntity: CashEntity
    lateinit var weatherSuccessResponse: WeatherSuccessResponse

    @Before
    fun prepareDependencies(){
        // Fake data source
        weatherSuccessResponse  = WeatherSuccessResponse(
            city = City(),
            cnt = 563,
            cod = "200" ,
            list = listOf(),
            message = 65
        )

        cashEntity = CashEntity(content = weatherSuccessResponse)

        // Initialize Repository
        repositoryImpl = RepositoryImpl(
            local = FakeLocalDataSource(cashEntity),
            remote = FakeRemoteDataSource(weatherSuccessResponse)
        )
    }


    @Test
    fun `getCash retrieveData checkIfNotNull`() = mainCoroutineRule.runBlockingTest {
        // Given: insert cash entity to database
        repositoryImpl.insertCash(cashEntity)

        // When: get cash entity from database
        val result = repositoryImpl.getCash().first()

        // Then:  check if not null
        assertThat(result, IsNull.notNullValue())

    }

    @Test
    fun `insertCash insertFakeData CheckIfNotNull`() = mainCoroutineRule.runBlockingTest {
        // Given: fake cash entity
        // When: insert cash entity to database
        repositoryImpl.insertCash(cashEntity)
        // Then: get cash entity from database and check if not null
        val result = repositoryImpl.getCash().first()
        assertThat(result, IsNull.notNullValue())
    }

    @Test
    fun `deleteCash DeleteItem CheckNoData`()  = mainCoroutineRule.runBlockingTest {
        // Given: insert cash entity to database and retrieve it from database
        repositoryImpl.insertCash(cashEntity)
        val item = repositoryImpl.getCash().first()

        // When: delete cash entity to database
        repositoryImpl.deleteCash(item)

        // Then: get count from database and check if zero
        val result = repositoryImpl.getCash().count()
        assertThat(result, `is`(0))

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
}