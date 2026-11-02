package com.dogo.klotinecommercemvvm.presentation.screens.auth.register


import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dogo.klotinecommercemvvm.presentation.components.DefaultTopBar
import com.dogo.klotinecommercemvvm.presentation.screens.auth.register.components.Register
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
    Register(navController)
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    KlotinEcommerceMVVMTheme {
        RegisterScreen(rememberNavController())
    }
}