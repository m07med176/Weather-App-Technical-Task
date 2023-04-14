package com.alamiya.weatherapptask.presentation.weatherDetails

import androidx.annotation.RawRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alamiya.weatherapptask.data.source.dto.City
import com.alamiya.weatherapptask.domain.models.RegionsName
import com.alamiya.weatherapptask.domain.models.WeatherResponseModel
import com.alamiya.weatherapptask.domain.usecase.GetWeatherDetailsUseCase
import com.alamiya.weatherapptask.domain.usecase.UseCases
import com.alamiya.weatherapptask.domain.utils.DataResponseState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class WeatherDetailsViewModel(private val userCases: UseCases) : ViewModel() {
    private val _state =
        MutableStateFlow<DataResponseState<WeatherResponseModel>>(DataResponseState.OnLoading())
    val state: StateFlow<DataResponseState<WeatherResponseModel>>
        get() = _state


    private val _regions =
        MutableStateFlow<List<String>>(emptyList())
    val regions: StateFlow<List<String>>
        get() = _regions
    fun getRegions(@RawRes id:Int){
        viewModelScope.launch {
            userCases.getRegionsName.invoke(id).collect{
                _regions.value = it.map { it.name }
            }

        }
    }
    fun getWeatherData(city: String){
        viewModelScope.launch {
            userCases.getWeatherDetailsUseCase.invoke(city)
                .catch {
                    _state.value = DataResponseState.OnError(it.message.toString())
                }.collect{
                    _state.value = it
                }
        }
    }

}