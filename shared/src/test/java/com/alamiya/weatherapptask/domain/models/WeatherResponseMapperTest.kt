package com.alamiya.weatherapptask.domain.models

import com.alamiya.weatherapptask.data.source.dto.City
import com.alamiya.weatherapptask.data.source.dto.WeatherSuccessResponse
import org.hamcrest.Matchers
import org.junit.Assert.assertThat
import org.junit.Test

class WeatherResponseMapperTest {

    val weatherSuccessResponse  = WeatherSuccessResponse(
    city = City(),
    cod = "200" ,
    list = listOf(),
    )

    var weatherResponseMapper: WeatherResponseMapper = WeatherResponseMapper()

    @Test
    fun mapFromEntity() {
        // Given
        val item = weatherSuccessResponse
        // When
        val result = weatherResponseMapper.mapFromEntity(item)
        // Then
        assertThat(result, Matchers.instanceOf(WeatherResponseModel::class.java))
    }

}