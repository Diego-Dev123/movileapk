package com.dogo.klotinecommercemvvm.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(
    title: String,
    upAvailable: Boolean = false,
    navController: NavHostController? = null
) {

    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 19.sp,
                color = Color.Black
            )
        },

        colors = TopAppBarDefaults.topAppBarColors(
             containerColor = Color.White // nuevo
        ),

        navigationIcon = {
            if (upAvailable) {
                IconButton(onClick = { navController?.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "volver",
                        tint = Color.Black
                    )
                }
            }
        }
    )
}