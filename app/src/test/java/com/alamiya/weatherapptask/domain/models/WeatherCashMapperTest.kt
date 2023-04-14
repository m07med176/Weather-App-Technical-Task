package com.alamiya.weatherapptask.domain.models

import com.alamiya.weatherapptask.data.source.dto.CashEntity
import com.alamiya.weatherapptask.data.source.dto.City
import com.alamiya.weatherapptask.data.source.dto.WeatherSuccessResponse
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Assert.*

import org.junit.Test

class WeatherCashMapperTest {

    val weatherSuccessResponse  = WeatherSuccessResponse(
        city = City(),
        cnt = 563,
        cod = "200" ,
        list = listOf(),
        message = 65
    )

    val cashEntity = CashEntity(
        cityName = "London",
        content = weatherSuccessResponse
    )
    val weatherResponseModel = WeatherResponseModel(
        cityModel = City(),
        cnt = 563,
        cod = "200" ,
        list = listOf(),
        message = 65
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

    @Test
    fun entityFromMap() {
        // Given
        val item = weatherResponseModel
        // When
        val result = weatherCashMapper.entityFromMap(item)
        // Then
        assertThat(result, Matchers.instanceOf(CashEntity::class.java))
    }
}