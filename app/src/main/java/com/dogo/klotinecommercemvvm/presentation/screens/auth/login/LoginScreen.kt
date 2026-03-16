package com.dogo.klotinecommercemvvm.presentation.screens.auth.login

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.presentation.screens.auth.login.components.LoginContent

@Composable
fun LoginScreen(navController: NavHostController) {
    Scaffold() {paddingValues ->
        LoginContent(navController = navController,paddingValues)
    }
}