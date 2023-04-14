package com.alamiya.weatherapptask.domain.models

import com.alamiya.weatherapptask.data.source.dto.City
import com.alamiya.weatherapptask.data.source.dto.WeatherContent

data class WeatherResponseModel(
    val cityModel: City,
    val weatherList: List<WeatherContentModel>,
)