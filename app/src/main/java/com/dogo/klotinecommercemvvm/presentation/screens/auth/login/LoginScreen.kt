package com.dogo.klotinecommercemvvm.presentation.screens.auth.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.domain.util.Resource
import com.dogo.klotinecommercemvvm.presentation.components.AuthTransition
import com.dogo.klotinecommercemvvm.presentation.screens.auth.login.components.Login
import com.dogo.klotinecommercemvvm.presentation.screens.auth.login.components.LoginContent

@Composable
fun LoginScreen(navController: NavHostController, vm: LoginViewModel = hiltViewModel()) {
    Scaffold() {paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            LoginContent(navController = navController,paddingValues)

            Login(navController = navController)

            //tapa el login mientras se revisa la sesion o se entra a la app
            AnimatedVisibility(
                visible = vm.checkingSession || vm.loginResponse is Resource.Success,
                enter = fadeIn(tween(120)),
                exit = fadeOut(tween(350))
            ) {
                AuthTransition()
            }
        }
    }
}
