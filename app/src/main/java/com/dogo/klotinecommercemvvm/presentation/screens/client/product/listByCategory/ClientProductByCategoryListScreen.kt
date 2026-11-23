package com.dogo.klotinecommercemvvm.presentation.screens.client.product.listByCategory

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.domain.model.Category
import com.dogo.klotinecommercemvvm.presentation.components.DefaultTopBar
import com.dogo.klotinecommercemvvm.presentation.navigation.Graph
import com.dogo.klotinecommercemvvm.presentation.navigation.screen.admin.AdminCategoryScreen
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.list.components.AdminProductListContent
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.list.components.GetProducts
import com.dogo.klotinecommercemvvm.presentation.screens.client.product.listByCategory.components.GetProductsByCategory


@Composable
fun ClientProductByCategoryListScreen(navController: NavHostController, categoryParam: String?){
    Log.d("AdminProductListScreen", "category: $categoryParam")

    val categoryParse = Category.fromJson(categoryParam?: "").toJson()

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Productos",
                upAvailable = true,
                navController = navController
            )
        }
    ) {paddingValues ->
        GetProductsByCategory(navController= navController, paddingValues=paddingValues)
    }
}