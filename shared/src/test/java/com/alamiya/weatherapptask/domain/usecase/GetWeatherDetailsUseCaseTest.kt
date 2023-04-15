package com.alamiya.weatherapptask.domain.usecase

import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alamiya.weatherapptask.data.repository.FakeRepository
import com.alamiya.weatherapptask.data.source.dto.CashEntity
import com.alamiya.weatherapptask.data.source.dto.City
import com.alamiya.weatherapptask.data.source.dto.WeatherSuccessResponse
import com.alamiya.weatherapptask.data.source.local.FakeLocalDataSource
import com.alamiya.weatherapptask.data.source.remote.FakeRemoteDataSource
import com.alamiya.weatherapptask.domain.utils.DataResponseState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class GetWeatherDetailsUseCaseTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var repository: FakeRepository

    lateinit var getWeatherDetailsUseCase: GetWeatherDetailsUseCase

    @Before
    fun handler() {
        val weatherSuccessResponse = WeatherSuccessResponse(
            city = City(),
            cod = "200",
            list = listOf(),
        )
        val cashEntity = CashEntity(
            content = weatherSuccessResponse,
            cityName = "London"
        )

        val fakeLocalDataSource = FakeLocalDataSource(cashEntity)
        val fakeRemoteDataSource = FakeRemoteDataSource(weatherSuccessResponse)

        repository = FakeRepository(
            context = ApplicationProvider.getApplicationContext(),
            local = fakeLocalDataSource,
            remote = fakeRemoteDataSource
        )

        getWeatherDetailsUseCase = GetWeatherDetailsUseCase(repository)
    }

    @Test
    fun `getWeatherDetailsUseCase GetDataFromNetwork ReturnSuccessData`() = mainCoroutineRule.runBlockingTest {
        // Given
        val city = "London"

        // When
        val result = getWeatherDetailsUseCase.invoke(city).first()

        // Then
        when(result){
            is DataResponseState.OnSuccess -> {
                assertThat(result.data,notNullValue())
            }
            is DataResponseState.OnError -> {}
            is DataResponseState.OnNothingData -> {}
            is DataResponseState.OnLoading -> {}
        }
    }

    @Test
    fun `getWeatherDetailsUseCase GetDataFromCash ReturnSuccessData`() = mainCoroutineRule.runBlockingTest {
        // Given
        val city = "London"
        repository.isOnline = false

        // When
        val result = getWeatherDetailsUseCase.invoke(city).first()

        // Then
        when(result){
            is DataResponseState.OnSuccess -> {
                assertThat(result.data,notNullValue())
            }
            is DataResponseState.OnError -> {}
            is DataResponseState.OnNothingData -> {}
            is DataResponseState.OnLoading -> {}
        }
    }


}