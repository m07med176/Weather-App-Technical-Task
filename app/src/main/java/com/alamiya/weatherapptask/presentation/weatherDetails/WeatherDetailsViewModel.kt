package com.alamiya.weatherapptask.presentation.weatherDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alamiya.weatherapptask.data.source.dto.City
import com.alamiya.weatherapptask.domain.models.WeatherResponseModel
import com.alamiya.weatherapptask.domain.usecase.GetWeatherDetailsUseCase
import com.alamiya.weatherapptask.domain.utils.DataResponseState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class WeatherDetailsViewModel(private val userCases: GetWeatherDetailsUseCase) : ViewModel() {
    private val _state =
        MutableStateFlow<DataResponseState<WeatherResponseModel>>(DataResponseState.OnLoading())
    val state: StateFlow<DataResponseState<WeatherResponseModel>>
        get() = _state


    fun getWeatherData(city: String){
        viewModelScope.launch {
            userCases.invoke(city)
                .catch {
                    _state.value = DataResponseState.OnError(it.message.toString())
                }.collect{
                    _state.value = it
                }
        }
    }

}