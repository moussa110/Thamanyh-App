package com.mousa.thamnyahapp.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.mousa.thamnyahapp.R

@Composable
fun CustomAsyncImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    placeholder: Int = R.drawable.logo_number,
    error: Int = R.drawable.logo_number,
    contentDescription: String = "Image",
    contentScale: ContentScale = ContentScale.Crop
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        modifier = modifier
            .clipToBounds(),
        contentScale = contentScale,
        placeholder = painterResource(id = placeholder),
        error = painterResource(id = error)
    )
}