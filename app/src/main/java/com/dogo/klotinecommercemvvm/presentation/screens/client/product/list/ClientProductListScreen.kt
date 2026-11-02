package com.dogo.klotinecommercemvvm.presentation.screens.client.product.list

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.presentation.screens.client.category.list.components.ClientCategoryListContent
import com.dogo.klotinecommercemvvm.presentation.screens.client.product.list.components.ClientProductListContent


@Composable
fun ClientProductListScreen(){


    Scaffold() {paddingValues ->
        ClientProductListContent(paddingValues =paddingValues )
    }
}