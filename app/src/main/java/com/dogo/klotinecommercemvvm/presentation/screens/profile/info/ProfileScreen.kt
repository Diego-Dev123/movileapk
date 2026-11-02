package com.dogo.klotinecommercemvvm.presentation.screens.profile.info

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.presentation.screens.profile.info.components.ProfileContent


@Composable
fun ProfileScreen(navContoller: NavHostController){
    Scaffold() {paddingValues ->
        ProfileContent(paddingValues =paddingValues, navController = navContoller )
    }
}