package com.dogo.klotinecommercemvvm.presentation.screens.auth.register


import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dogo.klotinecommercemvvm.presentation.screens.auth.register.components.Register
import com.dogo.klotinecommercemvvm.presentation.screens.auth.register.components.RegisterContent
import com.dogo.klotinecommercemvvm.ui.theme.KlotinEcommerceMVVMTheme

@Composable
fun RegisterScreen(navController: NavHostController) {

    Scaffold { paddingValues ->
        RegisterContent(
            paddingValues = paddingValues,
            onBack = { navController.popBackStack() }
        )
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
