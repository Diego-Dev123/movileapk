package com.dogo.klotinecommercemvvm.presentation.screens.admin.product.list.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.domain.model.Product

@Composable
fun AdminProductListContent(
    navController: NavHostController,
    paddingValues: PaddingValues,
    products: List<Product>,
    onDelete: (Product) -> Unit
){

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .fillMaxSize(),
        //espacio para que el botón + no tape el último producto
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        items(items = products){product ->
            AdminProductListItem(navController, product, onDelete)

        }
    }
}