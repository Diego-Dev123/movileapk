package com.dogo.klotinecommercemvvm.presentation.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.dogo.klotinecommercemvvm.R
import com.dogo.klotinecommercemvvm.ui.theme.Blue_500
import com.dogo.klotinecommercemvvm.ui.theme.RolesBackground
import com.dogo.klotinecommercemvvm.ui.theme.Teal_500
import com.dogo.klotinecommercemvvm.ui.theme.grisOscuro
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun RoleSwitchScreen(onFinished: () -> Unit) {
    val appear = remember { Animatable(0f) }
    val swap = remember { Animatable(0f) }

    val dots = rememberInfiniteTransition(label = "dots")
    val dotAlphas = listOf(0, 180, 360).map { delayMs ->
        dots.animateFloat(
            initialValue = 0.25f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(tween(500), RepeatMode.Reverse, initialStartOffset = StartOffset(delayMs)),
            label = "dot$delayMs"
        )
    }

    LaunchedEffect(Unit) {
        appear.animateTo(1f, tween(300))
        //un solo intercambio
        swap.animateTo(1f, tween(1000, easing = FastOutSlowInEasing))
        delay(250)
        onFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(RolesBackground)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.graphicsLayer { alpha = appear.value },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(240.dp)
                    .height(170.dp),
                contentAlignment = Alignment.Center
            ) {
                val theta = swap.value * PI.toFloat()
                val depth = sin(theta)
                OrbitIcon(
                    icon = R.drawable.ic_role_admin,
                    colors = listOf(Blue_500, Color(0xFF2F8FD0)),
                    x = cos(theta) * 78f,
                    y = depth * 30f,
                    scale = 1f + 0.22f * depth,
                    z = depth
                )
                OrbitIcon(
                    icon = R.drawable.ic_role_client,
                    colors = listOf(Teal_500, Color(0xFF35B47A)),
                    x = -cos(theta) * 78f,
                    y = -depth * 30f,
                    scale = 1f - 0.22f * depth,
                    z = -depth
                )
            }

            Text(
                text = "Cambiando de rol",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = grisOscuro
            )
            Text(
                modifier = Modifier.padding(top = 6.dp),
                text = "Un momento, estamos preparando todo",
                fontSize = 14.sp,
                color = Color(0xFF6B7280)
            )
            Row(
                modifier = Modifier.padding(top = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                dotAlphas.forEach { alpha ->
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .graphicsLayer { this.alpha = alpha.value }
                            .background(Blue_500, CircleShape)
                    )
                }
            }
        }
    }
}

@Composable
private fun OrbitIcon(
    icon: Int,
    colors: List<Color>,
    x: Float,
    y: Float,
    scale: Float,
    z: Float
) {
    Box(
        modifier = Modifier
            .zIndex(z)
            .graphicsLayer {
                translationX = x.dp.toPx()
                translationY = y.dp.toPx()
                scaleX = scale
                scaleY = scale
            }
            .shadow(8.dp, CircleShape)
            .size(84.dp)
            .background(Brush.linearGradient(colors), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(44.dp),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.White
        )
    }
}
