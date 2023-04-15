package com.alamiya.jetbackcomposeview.presentation.screens

import androidx.annotation.RawRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alamiya.weatherapptask.domain.models.WeatherResponseModel
import com.alamiya.weatherapptask.domain.usecase.UseCases
import com.alamiya.weatherapptask.domain.utils.DataResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val userCases: UseCases
) : ViewModel() {

    var state: MutableState<DataResponseState<WeatherResponseModel>> = mutableStateOf(
        DataResponseState.OnLoading()
    )
    var regions: MutableState<List<String>> = mutableStateOf(emptyList())

    fun getRegions(@RawRes id: Int) {

        viewModelScope.launch {
            userCases.getRegionsName.invoke(id).collect {
                regions.value = it.map { it.name }
            }

        }
    }

    fun getWeatherData(city: String) {
        viewModelScope.launch {
            userCases.getWeatherDetailsUseCase.invoke(city)
                .catch {
                    state.value = DataResponseState.OnError(it.message.toString())
                }.collect {
                    state.value = it
                }
        }
    }
}