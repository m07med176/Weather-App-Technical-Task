package com.alamiya.jetbackcomposeview.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alamiya.jetbackcomposeview.presentation.components.*
import com.alamiya.weatherapptask.domain.models.WeatherContentModel
import com.alamiya.weatherapptask.domain.models.WeatherResponseModel
import com.alamiya.weatherapptask.domain.utils.DataResponseState

@Composable
fun WeatherDetailsScreen(viewModel: WeatherDetailsViewModel) {
    viewModel.getWeatherData("London")
    val state: DataResponseState<WeatherResponseModel> by viewModel.state
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            SearchTextField(
                modifier = Modifier.fillMaxWidth(),
                hint = "Search...",
                onSearch = { query ->
                    viewModel.getWeatherData(query)
                }
            )

            when (state) {
                is DataResponseState.OnError -> ErrorPage((state as DataResponseState.OnError<WeatherResponseModel>).message)
                is DataResponseState.OnLoading -> CustomLottieAnimationState(AnimationState.Loading)
                is DataResponseState.OnNothingData -> CustomLottieAnimationState(AnimationState.Nothing)
                is DataResponseState.OnSuccess -> FeedData((state as DataResponseState.OnSuccess<WeatherResponseModel>).data.weatherList)
            }
        }


    }


}

@Composable
fun FeedData(itemsData: List<WeatherContentModel>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(itemsData) { item ->
            ItemListItem(model = item) {

            }
        }
    }
}

@Composable
fun ItemListItem(
    modifier: Modifier = Modifier,
    model: WeatherContentModel,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 4.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = model.dt,
                color = Color.Black,
                fontSize = 15.sp,
                modifier = Modifier.padding(5.dp)
            )
            NetworkImage(url = model.image, modifier = Modifier.size(70.dp))

            Text(
                text = model.max,
                color = Color.Black,
                fontSize = 17.sp,
                modifier = Modifier.padding(5.dp)
            )
            Text(
                text = model.min,
                color = Color.Black,
                fontSize = 15.sp,
                modifier = Modifier.padding(5.dp)
            )
            Text(
                text = model.desc,
                color = Color.Black,
                fontSize = 15.sp,
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}