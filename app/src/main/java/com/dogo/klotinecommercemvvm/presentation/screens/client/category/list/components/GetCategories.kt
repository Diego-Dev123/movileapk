package com.dogo.klotinecommercemvvm.presentation.screens.client.category.list.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.domain.util.Resource
import com.dogo.klotinecommercemvvm.presentation.components.EmptyStateView
import com.dogo.klotinecommercemvvm.presentation.components.ErrorStateView
import com.dogo.klotinecommercemvvm.presentation.components.SkeletonList
import com.dogo.klotinecommercemvvm.presentation.screens.client.category.list.ClientCategoryListViewModel


@Composable
fun GetCategories(paddingValues: PaddingValues,navController: NavHostController,vm: ClientCategoryListViewModel = hiltViewModel()){
    when(val response = vm.categoriesResponse){
        null, Resource.Loading -> SkeletonList(paddingValues = paddingValues)
        is Resource.Success -> {
            if (response.data.isEmpty()) {
                EmptyStateView("Aún no hay categorías disponibles.", paddingValues = paddingValues)
            } else {
                ClientCategoryListContent(
                    paddingValues = paddingValues,
                    navController,
                    categories = response.data
                )
            }
        }
        is Resource.Failure -> ErrorStateView(
            message = response.message,
            onRetry = { vm.getCategories() },
            paddingValues = paddingValues
        )
    }
}
