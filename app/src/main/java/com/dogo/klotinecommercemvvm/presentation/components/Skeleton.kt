package com.dogo.klotinecommercemvvm.presentation.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SkeletonList(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    imageOnSide: Boolean = false,
    count: Int = 6
) {
    val transition = rememberInfiniteTransition(label = "skeleton")
    val alpha by transition.animateFloat(
        initialValue = 0.45f,
        targetValue = 0.95f,
        animationSpec = infiniteRepeatable(tween(900, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = "skeletonAlpha"
    )
    val color = Color(0xFFD9DCE3).copy(alpha = alpha)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(top = 8.dp)
            .clipToBounds()
            .semantics { contentDescription = "Cargando" }
    ) {
        repeat(count) {
            if (imageOnSide) SkeletonSideCard(color) else SkeletonTopCard(color)
        }
    }
}

@Composable
private fun SkeletonBlock(color: Color, modifier: Modifier, radius: Dp = 8.dp) {
    Spacer(modifier = modifier.background(color, RoundedCornerShape(radius)))
}

@Composable
private fun SkeletonSideCard(color: Color) {
    SkeletonCardFrame {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SkeletonBlock(color, Modifier.size(90.dp), 12.dp)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
            ) {
                SkeletonBlock(color, Modifier.fillMaxWidth(0.6f).height(16.dp))
                SkeletonBlock(color, Modifier.padding(top = 8.dp).fillMaxWidth().height(12.dp))
                SkeletonBlock(color, Modifier.padding(top = 6.dp).fillMaxWidth(0.4f).height(12.dp))
            }
            SkeletonBlock(color, Modifier.padding(start = 12.dp).size(36.dp), 18.dp)
        }
    }
}

@Composable
private fun SkeletonTopCard(color: Color) {
    SkeletonCardFrame {
        Column {
            SkeletonBlock(color, Modifier.fillMaxWidth().height(120.dp), 0.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    SkeletonBlock(color, Modifier.fillMaxWidth(0.5f).height(16.dp))
                    SkeletonBlock(color, Modifier.padding(top = 8.dp).fillMaxWidth(0.8f).height(12.dp))
                }
                SkeletonBlock(color, Modifier.padding(start = 12.dp).width(36.dp).height(36.dp), 18.dp)
            }
        }
    }
}

@Composable
private fun SkeletonCardFrame(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 6.dp, bottom = 6.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        content()
    }
}
