package com.dogo.klotinecommercemvvm.presentation.navigation.graph.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dogo.klotinecommercemvvm.presentation.navigation.Graph
import com.dogo.klotinecommercemvvm.presentation.navigation.graph.auth.AuthNavGraph
import com.dogo.klotinecommercemvvm.presentation.navigation.graph.roles.RolesNavGraph
import com.dogo.klotinecommercemvvm.presentation.screens.splash.RoleSwitchScreen
import com.dogo.klotinecommercemvvm.presentation.screens.splash.SplashScreen

@Composable
fun RootNavGraph(navController: NavHostController, startRoute: String = Graph.SPLASH){
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = startRoute
    ){
        composable(route = Graph.SPLASH){
            SplashScreen(onFinished = {
                navController.navigate(Graph.AUTH){
                    popUpTo(Graph.SPLASH){ inclusive = true }
                }
            })
        }
        composable(route = Graph.ROLE_SWITCH){
            RoleSwitchScreen(onFinished = {
                navController.navigate(Graph.ROLES){
                    popUpTo(Graph.ROLE_SWITCH){ inclusive = true }
                }
            })
        }
        AuthNavGraph(navController= navController)
        RolesNavGraph(navController= navController)
    }
}
