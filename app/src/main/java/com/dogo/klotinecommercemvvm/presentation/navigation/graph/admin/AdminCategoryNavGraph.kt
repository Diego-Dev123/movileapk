package com.dogo.klotinecommercemvvm.presentation.navigation.graph.admin

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.dogo.klotinecommercemvvm.presentation.navigation.Graph
import com.dogo.klotinecommercemvvm.presentation.navigation.screen.admin.AdminCategoryScreen
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.create.AdminCategoryCreateScreen
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.update.AdminCategoryUpdateScreen
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.create.AdminProductCreateScreen
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.list.AdminProductListScreen

fun NavGraphBuilder.AdminCategoryNavGraph(navController: NavHostController){
    navigation(
        route = Graph.ADMIN_CATEGORY,
        startDestination = AdminCategoryScreen.CategoryCreate.route
    ){
        composable(route = AdminCategoryScreen.CategoryCreate.route){
            AdminCategoryCreateScreen(navController)
        }

        composable(
            route = AdminCategoryScreen.CategoryUpdate.route,
            arguments = listOf(navArgument("category") {
                type = NavType.StringType
            })
        ){
            it.arguments?.getString("category")?.let {
                AdminCategoryUpdateScreen(navController, it)
            }

        }


        composable(
            route = AdminCategoryScreen.ProductList.route,
            arguments = listOf(navArgument("category") {
                type = NavType.StringType
            })
        ){
            it.arguments?.getString("category")?.let {
                AdminProductListScreen(navController,it)
            }

        }


        composable(
            route = AdminCategoryScreen.ProductCreate.route,
            arguments = listOf(navArgument("category") {
                type = NavType.StringType
            })
        ){
            it.arguments?.getString("category")?.let {
                AdminProductCreateScreen(navController,it)
            }

        }

    }
}