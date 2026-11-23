package com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.list.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.domain.model.Category
import com.dogo.klotinecommercemvvm.domain.util.Resource
import com.dogo.klotinecommercemvvm.presentation.components.EmptyStateView
import com.dogo.klotinecommercemvvm.presentation.components.ErrorStateView
import com.dogo.klotinecommercemvvm.presentation.components.SkeletonList
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.list.AdminCategoryListViewModel


@Composable
fun GetCategories(
    navController: NavHostController,
    paddingValues: PaddingValues,
    onDelete: (Category) -> Unit,
    vm: AdminCategoryListViewModel = hiltViewModel()
){
    when(val response = vm.categoriesResponse){
        null, Resource.Loading -> SkeletonList(paddingValues = paddingValues)
        is Resource.Success -> {
            if (response.data.isEmpty()) {
                EmptyStateView("Aún no hay categorías.", paddingValues = paddingValues)
            } else {
                AdminCategoryListContent(navController, categories = response.data, paddingValues = paddingValues, onDelete = onDelete)
            }
        }
        is Resource.Failure -> ErrorStateView(
            message = response.message,
            onRetry = { vm.getCategories() },
            paddingValues = paddingValues
        )
    }
}
