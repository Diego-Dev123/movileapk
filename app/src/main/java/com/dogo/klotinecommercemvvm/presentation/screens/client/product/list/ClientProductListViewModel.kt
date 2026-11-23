package com.dogo.klotinecommercemvvm.presentation.screens.client.product.list

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogo.klotinecommercemvvm.domain.model.Product
import com.dogo.klotinecommercemvvm.domain.useCase.products.ProductsUseCase
import com.dogo.klotinecommercemvvm.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientProductListViewModel @Inject constructor(private val productsUseCase: ProductsUseCase): ViewModel() {

    var productsResponse by mutableStateOf<Resource<List<Product>>>(Resource.Loading)

    init {
        getProducts()
    }

    private var job: Job? = null

    fun getProducts() {
        job?.cancel()
        job = viewModelScope.launch {
            if (productsResponse !is Resource.Success) productsResponse = Resource.Loading
            productsUseCase.findAll().collect {
                productsResponse = it
            }
        }
    }


}