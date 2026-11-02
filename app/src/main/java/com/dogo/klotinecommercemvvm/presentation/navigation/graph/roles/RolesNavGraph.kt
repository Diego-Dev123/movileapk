package com.dogo.klotinecommercemvvm.presentation.navigation.graph.roles

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dogo.klotinecommercemvvm.presentation.navigation.Graph
import com.dogo.klotinecommercemvvm.presentation.navigation.screen.roles.RolesScreen
import com.dogo.klotinecommercemvvm.presentation.screens.admin.home.AdminHomeScreen
import com.dogo.klotinecommercemvvm.presentation.screens.client.home.ClientHomeScreen
import com.dogo.klotinecommercemvvm.presentation.screens.roles.RolesScreen

fun NavGraphBuilder.RolesNavGraph(navController: NavHostController){
    navigation(
        route = Graph.ROLES,
        startDestination = RolesScreen.Roles.route
    ){
        composable(route = RolesScreen.Roles.route){
            RolesScreen(navController)
        }

        composable(route = Graph.CLIENT){
            ClientHomeScreen()
        }

        composable(route = Graph.ADMIN){
            AdminHomeScreen()
        }
    }
}