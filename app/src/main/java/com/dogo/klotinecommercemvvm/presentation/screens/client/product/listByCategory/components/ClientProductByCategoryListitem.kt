package com.dogo.klotinecommercemvvm.presentation.screens.client.product.listByCategory.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.domain.model.Product
import com.dogo.klotinecommercemvvm.presentation.components.ItemCard
import com.dogo.klotinecommercemvvm.presentation.navigation.screen.client.ClientProductScreen
import com.dogo.klotinecommercemvvm.presentation.screens.client.product.listByCategory.ClientProductByCategoryListViewModel

@Composable
fun ClientProductByCategoryListItem(navController: NavHostController, product: Product, vm: ClientProductByCategoryListViewModel = hiltViewModel()){

    Box(
        modifier = Modifier.clickable {
            navController.navigate(
                ClientProductScreen.ProductDetail.passProduct(product.toJson())
            )
        }
    ) {
        ItemCard(
            image = product.image1,
            title = product.name,
            description = product.description,
            price = product.price.toString(),
            imageOnSide = true
        )
    }
}
