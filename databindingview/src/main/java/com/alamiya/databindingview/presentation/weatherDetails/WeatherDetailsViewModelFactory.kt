package com.alamiya.databindingview.presentation.weatherDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alamiya.weatherapptask.domain.usecase.UseCases

class WeatherDetailsViewModelFactory constructor(private val useCases: UseCases) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(WeatherDetailsViewModel::class.java)) {
            WeatherDetailsViewModel(this.useCases) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
