package com.alamiya.weatherapptask.domain.models

import com.alamiya.weatherapptask.data.source.dto.City
import com.alamiya.weatherapptask.data.source.dto.WeatherContent

data class WeatherResponseModel(
    val cityModel: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherContent>,
    val message: Int
)