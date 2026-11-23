package com.dogo.klotinecommercemvvm.presentation.components

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dogo.klotinecommercemvvm.R

@Composable
fun JumpingCart(size: Dp, modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "jumpingCart")
    val jump = transition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 1700
                0f at 0
                -26f at 420 using FastOutSlowInEasing
                0f at 840 using FastOutLinearInEasing
                -8f at 980 using FastOutSlowInEasing
                0f at 1120 using FastOutLinearInEasing
                0f at 1700
            },
            repeatMode = RepeatMode.Restart
        ),
        label = "jump"
    )

    Image(
        modifier = modifier
            .size(size)
            .graphicsLayer { translationY = jump.value.dp.toPx() },
        painter = painterResource(id = R.drawable.shopping_cart),
        contentDescription = "Logo"
    )
}
