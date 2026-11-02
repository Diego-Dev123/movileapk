package com.dogo.klotinecommercemvvm.presentation.navigation.graph.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dogo.klotinecommercemvvm.presentation.navigation.Graph
import com.dogo.klotinecommercemvvm.presentation.navigation.screen.auth.AuthScreen
import com.dogo.klotinecommercemvvm.presentation.screens.auth.login.LoginScreen
import com.dogo.klotinecommercemvvm.presentation.screens.auth.register.RegisterScreen

fun NavGraphBuilder.AuthNavGraph(navController: NavHostController){
    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreen.Login.route
    ){
        composable(route = AuthScreen.Login.route){
            LoginScreen(navController)
        }

        composable(route = AuthScreen.Register.route){
            RegisterScreen(navController)
        }


//        composable(route = AuthScreen.Roles.route){
//            RolesScreen(navController)
//        }
//        composable(route = AuthScreen.AdminHome.route){
//            AdminHomeScreen(navController)
//        }
//        composable(route = AuthScreen.ClientHome.route){
//            ClientHomeScreen(navController)
//        }
    }
}