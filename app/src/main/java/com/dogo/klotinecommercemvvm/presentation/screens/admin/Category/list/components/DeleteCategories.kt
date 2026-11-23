package com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.list.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.dogo.klotinecommercemvvm.domain.util.Resource
import com.dogo.klotinecommercemvvm.presentation.components.LocalAppMessenger
import com.dogo.klotinecommercemvvm.presentation.components.MessageType
import com.dogo.klotinecommercemvvm.presentation.components.ProgressBar
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.list.AdminCategoryListViewModel


@Composable
fun DeleteCategory(vm: AdminCategoryListViewModel = hiltViewModel()){
    val messenger = LocalAppMessenger.current
    val response = vm.deleteCategoryResponse

    LaunchedEffect(response) {
        when(response){
            is Resource.Success -> {
                messenger.show("Categoría movida a la papelera", MessageType.Success)
                vm.clearDeleteResponse()
            }
            is Resource.Failure -> {
                messenger.show(response.message, MessageType.Error)
                vm.clearDeleteResponse()
            }
            else -> {}
        }
    }

    if (response is Resource.Loading) {
        ProgressBar()
    }
}
