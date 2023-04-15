package com.alamiya.weatherapptask.data.source.dto

data class Weather(
    val description: String ="",
    val icon: String ="",
    val id: Int?=null,
    val main: String =""
)