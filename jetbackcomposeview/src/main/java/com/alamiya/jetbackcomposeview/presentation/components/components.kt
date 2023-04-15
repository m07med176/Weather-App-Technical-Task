package com.alamiya.jetbackcomposeview.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.alamiya.jetbackcomposeview.R

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    hint: String = "Search...",
//    data:List<T>,
    onSearch: (String) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    OutlinedTextField(
        label = { Text(text = "Region Name") },
        modifier = modifier,
        value = searchText,
        onValueChange = {
            searchText = it
            isExpanded = true
            onSearch(it)
        },
        placeholder = { Text(hint) },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search"
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(searchText)
            }
        ),
        textStyle = TextStyle(
            color = MaterialTheme.colors.onSurface
        ),
//        colors = TextFieldDefaults.textFieldColors(
//            backgroundColor = MaterialTheme.colors.surface
//        )
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            unfocusedBorderColor = Color.Gray
        )
    )


//    DropdownMenu(
//        expanded = searchText.isNotEmpty(),
//        onDismissRequest = { },
//        modifier = Modifier.fillMaxWidth(),
//        // This line here will accomplish what you want
//        properties = PopupProperties(focusable = false)
//    ) {
//        data.forEach { label ->
//            DropdownMenuItem(onClick = {
//
//            }) {
//
//            }
//        }
//    }


}

@Composable
fun NetworkImage(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    val painter = rememberImagePainter(
        data = url,
        builder = {
            crossfade(true)
        }
    )

    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .size(150.dp)
            .clip(RoundedCornerShape(8.dp))
    )
}


enum class AnimationState {
    Error,
    Loading,
    Nothing
}

@Composable
fun CustomLottieAnimationState(animationState: AnimationState) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            when (animationState) {
                AnimationState.Error -> R.raw.state_error
                AnimationState.Loading -> R.raw.state_loading
                AnimationState.Nothing -> R.raw.state_nothing_data
            }
        )
    )
    LottieAnimation(
        composition = composition,
        modifier = Modifier.fillMaxSize(),
        iterations = LottieConstants.IterateForever
    )
}

@Composable
fun ErrorPage(message: String) {
    CustomLottieAnimationState(AnimationState.Error)
    CustomSnakeBar(true, message)
}

@Composable
fun CustomSnakeBar(isError: Boolean = false, message: String) {
    Snackbar(
        backgroundColor = if (!isError) Color.Green else Color.Red
    ) {
        Text(message)
    }
}