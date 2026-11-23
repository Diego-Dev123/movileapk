package com.dogo.klotinecommercemvvm.presentation.navigation.graph.client

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.dogo.klotinecommercemvvm.presentation.navigation.Graph
import com.dogo.klotinecommercemvvm.presentation.navigation.screen.admin.AdminCategoryScreen
import com.dogo.klotinecommercemvvm.presentation.navigation.screen.client.ClientCategoryScreen
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.create.AdminCategoryCreateScreen
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.update.AdminCategoryUpdateScreen
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.create.AdminProductCreateScreen
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.list.AdminProductListScreen
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.update.AdminProductUpdateScreen
import com.dogo.klotinecommercemvvm.presentation.screens.client.product.listByCategory.ClientProductByCategoryListScreen

fun NavGraphBuilder.ClientCategoryNavGraph(navController: NavHostController){
    navigation(
        route = Graph.CLIENT_CATEGORY,
        startDestination = ClientCategoryScreen.ProductList.route
    ){


        composable(
            route = ClientCategoryScreen.ProductList.route,
            arguments = listOf(navArgument("category") {
                type = NavType.StringType
            })
        ){
            it.arguments?.getString("category")?.let {
                ClientProductByCategoryListScreen(navController,it)
            }

        }


    }
}