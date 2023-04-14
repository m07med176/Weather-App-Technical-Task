package com.alamiya.weatherapptask.domain.models

import com.alamiya.weatherapptask.data.source.dto.City
import com.alamiya.weatherapptask.data.source.dto.WeatherSuccessResponse
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Assert.*

import org.junit.Test

class WeatherResponseMapperTest {

    val weatherSuccessResponse  = WeatherSuccessResponse(
    city = City(),
    cnt = 563,
    cod = "200" ,
    list = listOf(),
    message = 65
    )
    val weatherResponseModel = WeatherResponseModel(
        cityModel = City(),
        cnt = 563,
        cod = "200" ,
        list = listOf(),
        message = 65
    )

    var weatherResponseMapper: WeatherResponseMapper = WeatherResponseMapper()

    @Test
    fun mapFromEntity() {
        // Given
        val item = weatherSuccessResponse
        // When
        val result = weatherResponseMapper.mapFromEntity(item)
        // Then
        Assert.assertThat(result, Matchers.instanceOf(WeatherResponseModel::class.java))
    }

    @Test
    fun entityFromMap() {
        // Given
        val item = weatherResponseModel
        // When
        val result = weatherResponseMapper.entityFromMap(item)
        // Then
        Assert.assertThat(result, Matchers.instanceOf(WeatherSuccessResponse::class.java))
    }
}