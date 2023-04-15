package com.alamiya.weatherapptask.domain.models

import com.alamiya.weatherapptask.data.source.dto.CashEntity
import com.alamiya.weatherapptask.data.source.dto.City
import com.alamiya.weatherapptask.data.source.dto.WeatherSuccessResponse
import org.hamcrest.Matchers
import org.junit.Assert.assertThat
import org.junit.Test

class WeatherCashMapperTest {

    val weatherSuccessResponse  = WeatherSuccessResponse(
        city = City(),
        cod = "200" ,
        list = listOf(),
    )

    val cashEntity = CashEntity(
        cityName = "London",
        content = weatherSuccessResponse
    )


    val weatherCashMapper = WeatherCashMapper()

    @Test
    fun mapFromEntity() {
        // Given
        val item = cashEntity
        // When
        val result = weatherCashMapper.mapFromEntity(item)
        // Then
        assertThat(result, Matchers.instanceOf(WeatherResponseModel::class.java))
    }


}