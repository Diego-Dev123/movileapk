package com.dogo.klotinecommercemvvm.presentation.screens.client.home

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dogo.klotinecommercemvvm.presentation.navigation.graph.client.ClientNavGraph
import com.dogo.klotinecommercemvvm.presentation.screens.client.home.components.ClientButtonBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ClientHomeScreen(navController: NavHostController = rememberNavController()){
    Scaffold(
        bottomBar = {ClientButtonBar(navController = navController)}
    ) { paddingValues ->

        ClientNavGraph(navController = navController)
    }
}