package com.dogo.klotinecommercemvvm.presentation.screens.roles.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.dogo.klotinecommercemvvm.R
import com.dogo.klotinecommercemvvm.domain.model.Rol
import com.dogo.klotinecommercemvvm.presentation.navigation.Graph
import com.dogo.klotinecommercemvvm.ui.theme.Blue_500
import com.dogo.klotinecommercemvvm.ui.theme.Teal_500
import com.dogo.klotinecommercemvvm.ui.theme.grisOscuro
import kotlinx.coroutines.delay

@Composable
fun RolesItem(rol: Rol, index: Int, navController: NavHostController){
    val progress = remember { Animatable(0f) }

    LaunchedEffect(rol.id) {
        delay(index * 130L)
        progress.animateTo(1f, tween(450, easing = FastOutSlowInEasing))
    }

    val subtitle = when (rol.route) {
        Graph.ADMIN -> "Gestiona productos, categorías y papelera"
        Graph.CLIENT -> "Explora el catálogo y compra"
        else -> ""
    }
    val shape = RoundedCornerShape(24.dp)
    val iconRes = when (rol.route) {
        Graph.ADMIN -> R.drawable.ic_role_admin
        Graph.CLIENT -> R.drawable.ic_role_client
        else -> null
    }
    val iconColors = if (rol.route == Graph.ADMIN) {
        listOf(Blue_500, Color(0xFF2F8FD0))
    } else {
        listOf(Teal_500, Color(0xFF35B47A))
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                alpha = progress.value
                translationY = (1f - progress.value) * 60f
            }
            .clip(shape)
            .clickable {
                navController.navigate(route = rol.route) {
                    popUpTo(route = Graph.ROLES)
                }
            },
        shape = shape,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (iconRes != null) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .background(Brush.linearGradient(iconColors), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(38.dp),
                        painter = painterResource(id = iconRes),
                        contentDescription = rol.name,
                        tint = Color.White
                    )
                }
            } else {
                AsyncImage(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF2F4F7)),
                    model = rol.image,
                    contentDescription = rol.name,
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = rol.name,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = grisOscuro
                )
                if (subtitle.isNotEmpty()) {
                    Text(
                        modifier = Modifier.padding(top = 2.dp),
                        text = subtitle,
                        fontSize = 13.sp,
                        lineHeight = 17.sp,
                        color = Color(0xFF6B7280)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(Blue_500.copy(alpha = 0.15f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = Blue_500
                )
            }
        }
    }
}
