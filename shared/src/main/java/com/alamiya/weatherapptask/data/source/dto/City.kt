package com.alamiya.weatherapptask.data.source.dto

data class City(
    val coord: Coord? = null,
    val country: String? = null,
    val id: Int? = null,
    val name: String? = null,
    val population: Int? = null,
    val sunrise: Int = 0,
    val sunset: Int? = null,
    val timezone: Int? = null
)