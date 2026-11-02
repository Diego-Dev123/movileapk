package com.dogo.klotinecommercemvvm.presentation.screens.profile.update

import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.presentation.components.DefaultTopBar
import com.dogo.klotinecommercemvvm.presentation.screens.profile.info.components.ProfileContent
import com.dogo.klotinecommercemvvm.presentation.screens.profile.update.components.ProfileUpdateContent
import com.dogo.klotinecommercemvvm.presentation.screens.profile.update.components.UpdateUser


@Composable
fun ProfileUpdateScreen(navController: NavHostController, userParam: String){

    Log.d("ProfileUpdateScreen", "userParam: $userParam")
    Scaffold(
        topBar = {
            DefaultTopBar(
                navController = navController,
                title = "Actualizar perfil",
                upAvailable = true
            )
        }
    ) {paddingValues ->
        ProfileUpdateContent(paddingValues =paddingValues )
    }

    UpdateUser()
}