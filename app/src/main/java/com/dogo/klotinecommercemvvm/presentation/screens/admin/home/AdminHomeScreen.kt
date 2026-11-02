package com.dogo.klotinecommercemvvm.presentation.screens.admin.home

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dogo.klotinecommercemvvm.presentation.navigation.graph.admin.AdminNavGraph
import com.dogo.klotinecommercemvvm.presentation.screens.admin.home.components.AdminButtonBar

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun AdminHomeScreen(navController: NavHostController = rememberNavController()){
    Scaffold(
        bottomBar = { AdminButtonBar(navController = navController) }
    ) { paddingValues ->

        AdminNavGraph(navController = navController)
    }
}