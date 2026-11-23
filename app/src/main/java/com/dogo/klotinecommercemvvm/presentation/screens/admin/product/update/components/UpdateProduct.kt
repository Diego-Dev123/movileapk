package com.dogo.klotinecommercemvvm.presentation.screens.admin.product.update.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.dogo.klotinecommercemvvm.domain.util.Resource
import com.dogo.klotinecommercemvvm.presentation.components.LocalAppMessenger
import com.dogo.klotinecommercemvvm.presentation.components.MessageType
import com.dogo.klotinecommercemvvm.presentation.components.ProgressBar
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.update.AdminProductUpdateViewModel


@Composable
fun UpdateProduct(vm: AdminProductUpdateViewModel = hiltViewModel()){
    val messenger = LocalAppMessenger.current
    when(val response = vm.productResponse){
        Resource.Loading -> ProgressBar()
        is Resource.Success -> LaunchedEffect(response) {
            messenger.show("Producto actualizado", MessageType.Success)
        }
        is Resource.Failure -> LaunchedEffect(response) {
            messenger.show(response.message, MessageType.Error)
        }
        else -> if (response != null) {
            LaunchedEffect(response) {
                messenger.show("Error desconocido", MessageType.Error)
            }
        }
    }
}
