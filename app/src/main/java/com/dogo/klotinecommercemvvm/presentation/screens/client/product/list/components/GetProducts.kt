package com.dogo.klotinecommercemvvm.presentation.screens.client.product.list.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.domain.util.Resource
import com.dogo.klotinecommercemvvm.presentation.components.EmptyStateView
import com.dogo.klotinecommercemvvm.presentation.components.ErrorStateView
import com.dogo.klotinecommercemvvm.presentation.components.SkeletonList
import com.dogo.klotinecommercemvvm.presentation.screens.client.product.list.ClientProductListViewModel


@Composable
fun GetProducts(navController: NavHostController,paddingValues: PaddingValues,vm: ClientProductListViewModel = hiltViewModel()){

    LaunchedEffect(Unit) {
        vm.getProducts()
    }
    when(val response = vm.productsResponse){
        Resource.Loading -> SkeletonList(paddingValues = paddingValues, imageOnSide = true)
        is Resource.Success -> {
            if (response.data.isEmpty()) {
                EmptyStateView("Aún no hay productos disponibles.", paddingValues = paddingValues)
            } else {
                ClientProductListContent(
                    navController = navController,
                    paddingValues = paddingValues,
                    products = response.data
                )
            }
        }
        is Resource.Failure -> ErrorStateView(
            message = response.message,
            onRetry = { vm.getProducts() },
            paddingValues = paddingValues
        )
    }
}
