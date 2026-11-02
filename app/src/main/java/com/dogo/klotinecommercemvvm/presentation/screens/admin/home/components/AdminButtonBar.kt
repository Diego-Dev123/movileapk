package com.dogo.klotinecommercemvvm.presentation.screens.admin.home.components

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dogo.klotinecommercemvvm.presentation.navigation.screen.admin.AdminScreen


@Composable
fun AdminButtonBar(navController: NavHostController){
    val screens = listOf(
       // AdminScreen.productList,
        AdminScreen.categoryList,
        AdminScreen.profile
    )

    val navBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackEntry?.destination
    val ButtonBarDestination = screens.any{ it.route == currentDestination?.route}


    if (ButtonBarDestination){
        NavigationBar{
            screens.forEach {screen ->
                AdminButtonBarItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }

}