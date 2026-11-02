package com.dogo.klotinecommercemvvm.presentation.navigation.graph.client

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dogo.klotinecommercemvvm.presentation.navigation.Graph
import com.dogo.klotinecommercemvvm.presentation.navigation.graph.profile.ProfileNavGraph
import com.dogo.klotinecommercemvvm.presentation.navigation.screen.client.ClientScreen
import com.dogo.klotinecommercemvvm.presentation.screens.client.category.list.ClientCategoryListScreen
import com.dogo.klotinecommercemvvm.presentation.screens.client.product.list.ClientProductListScreen
import com.dogo.klotinecommercemvvm.presentation.screens.profile.info.ProfileScreen

@Composable
fun ClientNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        route = Graph.CLIENT,
        startDestination = ClientScreen.productList.route
    ){
        composable(route = ClientScreen.productList.route){
            ClientProductListScreen()
        }
        composable(route = ClientScreen.categoryList.route){
            ClientCategoryListScreen(navController)
        }
        composable(route = ClientScreen.profile.route){
            ProfileScreen(navController)
        }

        ProfileNavGraph(navController)
    }
}