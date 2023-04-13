package com.alamiya.weatherapptask.data.source.dto

data class WeatherSuccessResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherContent>,
    val message: Int
)