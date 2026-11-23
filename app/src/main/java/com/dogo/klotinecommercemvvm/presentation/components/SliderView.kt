package com.optic.ecommerceappmvvm.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.size.Scale
import com.dogo.klotinecommercemvvm.R


@Composable
fun SliderView(state: PagerState, images: List<String>) {

    HorizontalPager(
        state = state,
        modifier = Modifier
            .height(325.dp)
            .fillMaxWidth()
            .background(color = Color.Gray)
    ) { page ->
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(images[page])
                .scale(Scale.FILL)
                .build(),
            placeholder = painterResource(R.drawable.user_image) // ← forma correcta
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}