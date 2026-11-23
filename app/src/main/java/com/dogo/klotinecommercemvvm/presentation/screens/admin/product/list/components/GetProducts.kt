package com.dogo.klotinecommercemvvm.presentation.screens.admin.product.list.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.domain.model.Product
import com.dogo.klotinecommercemvvm.domain.util.Resource
import com.dogo.klotinecommercemvvm.presentation.components.EmptyStateView
import com.dogo.klotinecommercemvvm.presentation.components.ErrorStateView
import com.dogo.klotinecommercemvvm.presentation.components.SkeletonList
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.list.AdminProductListViewModel


@Composable
fun GetProducts(
    navController: NavHostController,
    paddingValues: PaddingValues,
    onDelete: (Product) -> Unit,
    vm: AdminProductListViewModel = hiltViewModel()
){
    LaunchedEffect(Unit) {
        vm.getProducts()
    }
    when(val response = vm.productsResponse){
        null, Resource.Loading -> SkeletonList(paddingValues = paddingValues, imageOnSide = true)
        is Resource.Success -> {
            if (response.data.isEmpty()) {
                EmptyStateView("Esta categoría aún no tiene productos.", paddingValues = paddingValues)
            } else {
                AdminProductListContent(
                    navController = navController,
                    paddingValues = paddingValues,
                    products = response.data,
                    onDelete = onDelete
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
