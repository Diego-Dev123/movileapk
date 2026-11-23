package com.dogo.klotinecommercemvvm.presentation.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dogo.klotinecommercemvvm.R
import com.dogo.klotinecommercemvvm.ui.theme.Teal_500

@Composable
fun AuthTransition(modifier: Modifier = Modifier, text: String = "Preparando tu cuenta") {
    val transition = rememberInfiniteTransition(label = "authTransition")
    val ring = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(1600, easing = LinearOutSlowInEasing)),
        label = "ring"
    )
    val bob = transition.animateFloat(
        initialValue = -7f,
        targetValue = 7f,
        animationSpec = infiniteRepeatable(tween(650, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = "bob"
    )
    val dot1 = dotAlpha(transition, 0)
    val dot2 = dotAlpha(transition, 160)
    val dot3 = dotAlpha(transition, 320)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Wave(ring.value)
        Wave((ring.value + 0.5f) % 1f)

        Image(
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer { translationY = bob.value.dp.toPx() },
            painter = painterResource(id = R.drawable.shopping_cart),
            contentDescription = null
        )

        Column(
            modifier = Modifier.offset(y = 130.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(dot1, dot2, dot3).forEach { alpha ->
                    Box(
                        modifier = Modifier
                            .size(9.dp)
                            .graphicsLayer { this.alpha = alpha.value }
                            .background(Teal_500, CircleShape)
                    )
                }
            }
            Text(
                modifier = Modifier.padding(top = 12.dp),
                text = text,
                fontSize = 14.sp,
                color = Color(0xFF6B7280)
            )
        }
    }
}

@Composable
private fun Wave(progress: Float) {
    Box(
        modifier = Modifier
            .size(180.dp)
            .graphicsLayer {
                val s = 0.6f + progress * 1.2f
                scaleX = s
                scaleY = s
                alpha = (1f - progress) * 0.28f
            }
            .background(Teal_500, CircleShape)
    )
}

@Composable
private fun dotAlpha(transition: InfiniteTransition, delay: Int): State<Float> =
    transition.animateFloat(
        initialValue = 0.25f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(450),
            RepeatMode.Reverse,
            initialStartOffset = StartOffset(delay)
        ),
        label = "dot$delay"
    )
