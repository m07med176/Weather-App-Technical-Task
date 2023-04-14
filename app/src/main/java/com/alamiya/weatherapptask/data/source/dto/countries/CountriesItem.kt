package com.alamiya.weatherapptask.data.source.dto.countries

data class CountriesItem(
    val capital: String,
    val name: String,
    val states: List<State>
)