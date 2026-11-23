package com.dogo.klotinecommercemvvm.presentation.screens.roles.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.presentation.components.JumpingCart
import com.dogo.klotinecommercemvvm.ui.theme.Blue_500
import com.dogo.klotinecommercemvvm.ui.theme.RolesBackground
import com.dogo.klotinecommercemvvm.ui.theme.Teal_500
import com.dogo.klotinecommercemvvm.ui.theme.grisOscuro
import com.dogo.klotinecommercemvvm.presentation.screens.roles.RolesViewModel

@Composable
fun RolesContent(paddingValues: PaddingValues, navController: NavHostController, vm: RolesViewModel = hiltViewModel()){
    val user = vm.authResponse.user
    val roles = user?.roles ?: emptyList()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(RolesBackground))
        )
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(Blue_500.copy(alpha = 0.14f), radius = size.width * 0.55f, center = Offset(size.width * 0.95f, size.height * 0.05f))
            drawCircle(Teal_500.copy(alpha = 0.14f), radius = size.width * 0.45f, center = Offset(size.width * 0.05f, size.height * 0.95f))
            drawCircle(Blue_500.copy(alpha = 0.10f), radius = size.width * 0.25f, center = Offset(size.width * 0.85f, size.height * 0.7f))
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            JumpingCart(size = 84.dp)

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = if (user?.name.isNullOrBlank()) "Bienvenido" else "Hola, ${user?.name}",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 26.sp,
                color = grisOscuro,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = "¿Cómo quieres ingresar hoy?",
                fontSize = 15.sp,
                color = Color(0xFF6B7280),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(28.dp))

            roles.forEachIndexed { index, rol ->
                RolesItem(rol = rol, index = index, navController = navController)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
