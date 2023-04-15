package com.alamiya.weatherapptask.domain.models

data class WeatherContentModel(
    var dt: String = "",
    var image: String = "",
    var max: String = "",
    var min: String = "",
    var desc: String = "",

    val feels_like: String = "",
    val humidity: String = "",
    val pressure: String = "",
    var temp: String = "",
    val visibility: String = "",
    val wind: String = "",
    val cloud: String = "",
    val windGust: String = ""
)
