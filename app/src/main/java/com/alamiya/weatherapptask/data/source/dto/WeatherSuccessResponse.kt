package com.alamiya.weatherapptask.data.source.dto

data class WeatherSuccessResponse(
    val city: City,
    val cod: String?=null,
    val list: List<WeatherContent> = emptyList(),
)