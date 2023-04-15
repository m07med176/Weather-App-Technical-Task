package com.alamiya.jetbackcomposeview.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alamiya.jetbackcomposeview.presentation.theme.WeatherAppTaskTheme

@Composable
fun WeatherDetailsScreen(viewModel: WeatherDetailsViewModel) {
    viewModel.getWeatherData("London")
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Greeting("Android")
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WeatherAppTaskTheme {
        Greeting("Android")
    }
}