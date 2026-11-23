package com.dogo.klotinecommercemvvm.presentation.screens.admin.product.list.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.dogo.klotinecommercemvvm.domain.util.Resource
import com.dogo.klotinecommercemvvm.presentation.components.LocalAppMessenger
import com.dogo.klotinecommercemvvm.presentation.components.MessageType
import com.dogo.klotinecommercemvvm.presentation.components.ProgressBar
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.list.AdminProductListViewModel


@Composable
fun DeleteProducts(vm: AdminProductListViewModel = hiltViewModel()){
    val messenger = LocalAppMessenger.current
    val response = vm.productsDeleteResponse

    LaunchedEffect(response) {
        when(response){
            is Resource.Success -> {
                messenger.show("Producto movido a la papelera", MessageType.Success)
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
