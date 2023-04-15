package com.alamiya.jetbackcomposeview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.alamiya.jetbackcomposeview.presentation.screens.WeatherDetailsScreen
import com.alamiya.jetbackcomposeview.presentation.screens.WeatherDetailsViewModel
import com.alamiya.jetbackcomposeview.presentation.theme.WeatherAppTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTaskTheme {
                val weatherDetailsViewModel: WeatherDetailsViewModel = hiltViewModel()
                WeatherDetailsScreen(viewModel = weatherDetailsViewModel)
            }
        }
    }
}
