package com.dogo.klotinecommercemvvm.presentation.screens.auth.register

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dogo.klotinecommercemvvm.presentation.components.DefaultTopBar
import com.dogo.klotinecommercemvvm.presentation.screens.auth.register.components.RegisterContent
import com.dogo.klotinecommercemvvm.ui.theme.KlotinEcommerceMVVMTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavHostController) {

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Registro",
                upAvailable = true,
                navController = navController
            )
        }
    ) { paddingValues ->
        RegisterContent(paddingValues)

    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    KlotinEcommerceMVVMTheme {
        RegisterScreen(rememberNavController())
    }
}