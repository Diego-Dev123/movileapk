package com.dogo.klotinecommercemvvm.presentation.screens.client.home.components

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dogo.klotinecommercemvvm.presentation.navigation.screen.client.ClientScreen

@Composable
fun ClientButtonBar(navController: NavHostController){
    val screens = listOf(
        ClientScreen.productList,
        ClientScreen.categoryList,
        ClientScreen.profile
    )

    val navBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackEntry?.destination
    val ButtonBarDestination = screens.any{ it.route == currentDestination?.route}


    if (ButtonBarDestination){
        NavigationBar{
            screens.forEach {screen ->
                ClientButtonBarItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }

}