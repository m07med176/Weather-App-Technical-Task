package com.alamiya.databindingview.presentation.weatherDetails

import android.text.Editable
import android.text.TextWatcher
import androidx.annotation.RawRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alamiya.weatherapptask.domain.models.WeatherResponseModel
import com.alamiya.weatherapptask.domain.usecase.UseCases
import com.alamiya.weatherapptask.domain.utils.DataResponseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class WeatherDetailsViewModel(private val userCases: UseCases) : ViewModel() {
    var firstOpen: Boolean = true

    private val _state =
        MutableStateFlow<DataResponseState<WeatherResponseModel>>(DataResponseState.OnLoading())
    val state: StateFlow<DataResponseState<WeatherResponseModel>>
        get() = _state


    private val _regions =
        MutableStateFlow<List<String>>(emptyList())
    val regions: StateFlow<List<String>>
        get() = _regions

    fun getRegions(@RawRes id: Int) {
        viewModelScope.launch {
            userCases.getRegionsName.invoke(id).collect {
                _regions.value = it.map { it.name }
            }

        }
    }

    fun getWeatherData(city: CharSequence) {
        viewModelScope.launch {
            userCases.getWeatherDetailsUseCase.invoke(city.toString())
                .catch {
                    _state.value = DataResponseState.OnError(it.message.toString())
                }.collect {
                    _state.value = it
                }
        }
    }


    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(query: CharSequence?, start: Int, before: Int, count: Int) {
            if (firstOpen) {
                firstOpen = false
            }
            if (query.toString().isBlank())
                _state.value = DataResponseState.OnNothingData()
            else
                getWeatherData(query.toString())
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }
}