package com.dogo.klotinecommercemvvm.presentation.screens.client.detail

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.presentation.screens.client.detail.components.ClientProductDetailContent

@Composable
fun ClientProductDetailScreen(navController: NavHostController, productParam: String){
    Scaffold() {paddingValues ->
        ClientProductDetailContent(paddingValues)
    }
}