package com.hgshkt.vrgsofttask.presentation.screens.image

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@Composable
fun ImageScreen(
    imageUrl: String?
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Publication image",
        modifier = Modifier.fillMaxSize()
    )
}