package com.dogo.klotinecommercemvvm.presentation.screens.client.product.list.components

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.domain.model.Product
import com.dogo.klotinecommercemvvm.presentation.components.ItemCard
import com.dogo.klotinecommercemvvm.presentation.screens.client.product.list.ClientProductListViewModel

@Composable
fun ClientProductListItem(navController: NavHostController, product: Product, vm: ClientProductListViewModel = hiltViewModel()){
    ItemCard(
        image = product.image1,
        title = product.name,
        description = product.description,
        price = product.price.toString(),
        imageOnSide = true
    )
}
