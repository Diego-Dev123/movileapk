package com.dogo.klotinecommercemvvm.presentation.screens.client.product.list.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.domain.model.Product
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.list.components.AdminProductListItem


@Composable
fun ClientProductListContent(navController: NavHostController, paddingValues: PaddingValues, products: List<Product>){

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .fillMaxSize()
    ) {
        items(items = products){product ->
            ClientProductListItem(navController, product
            )

        }
    }
}