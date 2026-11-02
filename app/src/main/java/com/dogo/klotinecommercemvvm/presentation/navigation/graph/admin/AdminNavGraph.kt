package com.dogo.klotinecommercemvvm.presentation.navigation.graph.admin

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dogo.klotinecommercemvvm.presentation.navigation.Graph
import com.dogo.klotinecommercemvvm.presentation.navigation.graph.profile.ProfileNavGraph
import com.dogo.klotinecommercemvvm.presentation.navigation.screen.admin.AdminScreen
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.list.AdminCategoryListScreen
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.list.AdminProductListScreen
import com.dogo.klotinecommercemvvm.presentation.screens.profile.info.ProfileScreen

@Composable
fun AdminNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        route = Graph.ADMIN,
        startDestination = AdminScreen.categoryList.route
    ){
//        composable(route = AdminScreen.productList.route){
//            AdminProductListScreen()
//        }
        composable(route = AdminScreen.categoryList.route){
            AdminCategoryListScreen(navController)
        }
        composable(route = AdminScreen.profile.route){
            ProfileScreen(navController)
        }

        ProfileNavGraph(navController)
        AdminCategoryNavGraph(navController)
    }
}