package com.dogo.klotinecommercemvvm.presentation.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dogo.klotinecommercemvvm.R
import com.dogo.klotinecommercemvvm.ui.theme.Teal_500
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(onFinished: () -> Unit) {
    val offsetX = remember { Animatable(-1f) }
    val scale = remember { Animatable(1f) }
    val wave = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        //llega desde la izquierda
        offsetX.animateTo(0f, tween(750, easing = FastOutSlowInEasing))
        //plum
        launch { wave.animateTo(1f, tween(600)) }
        scale.animateTo(0.8f, tween(90))
        scale.animateTo(1f, spring(Spring.DampingRatioHighBouncy, Spring.StiffnessMedium))
        delay(450)
        onFinished()
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        val widthPx = constraints.maxWidth.toFloat()

        Box(
            modifier = Modifier
                .size(180.dp)
                .graphicsLayer {
                    val s = 0.6f + wave.value * 1.4f
                    scaleX = s
                    scaleY = s
                    alpha = (1f - wave.value) * 0.35f
                }
                .background(Teal_500, CircleShape)
        )

        Image(
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer {
                    translationX = offsetX.value * widthPx
                    rotationZ = offsetX.value * -14f
                    scaleX = scale.value
                    scaleY = scale.value
                },
            painter = painterResource(id = R.drawable.shopping_cart),
            contentDescription = null
        )
    }
}
