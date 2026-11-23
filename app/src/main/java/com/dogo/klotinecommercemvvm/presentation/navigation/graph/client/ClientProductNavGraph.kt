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
import com.dogo.klotinecommercemvvm.presentation.navigation.screen.client.ClientProductScreen
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.create.AdminCategoryCreateScreen
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.update.AdminCategoryUpdateScreen
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.create.AdminProductCreateScreen
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.list.AdminProductListScreen
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.update.AdminProductUpdateScreen
import com.dogo.klotinecommercemvvm.presentation.screens.client.detail.ClientProductDetailScreen
import com.dogo.klotinecommercemvvm.presentation.screens.client.product.listByCategory.ClientProductByCategoryListScreen

fun NavGraphBuilder.ClientProductNavGraph(navController: NavHostController){
    navigation(
        route = Graph.CLIENT_PRODUCT,
        startDestination = ClientProductScreen.ProductDetail.route
    ){


        composable(
            route = ClientProductScreen.ProductDetail.route,
            arguments = listOf(navArgument("product") {
                type = NavType.StringType
            })
        ){
            it.arguments?.getString("product")?.let {
                ClientProductDetailScreen(navController,it)
            }

        }


    }
}