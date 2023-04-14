package com.alamiya.weatherapptask.presentation.weatherDetails

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
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
    fun getWeatherData(city: CharSequence){
        viewModelScope.launch {
            userCases.getWeatherDetailsUseCase.invoke(city.toString())
                .catch {
                    _state.value = DataResponseState.OnError(it.message.toString())
                }.collect{
                    _state.value = it
                }
        }
    }


    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(query: CharSequence?, start: Int, before: Int, count: Int) {
            if (query.toString().isBlank())
                _state.value = DataResponseState.OnNothingData()
            else
                getWeatherData(query.toString())
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }
}