package com.alamiya.weatherapptask.data.source.dto

data class WeatherContent(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Double,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)