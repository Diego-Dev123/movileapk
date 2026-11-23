package com.dogo.klotinecommercemvvm.presentation.screens.admin.product.list

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogo.klotinecommercemvvm.domain.model.Category
import com.dogo.klotinecommercemvvm.domain.model.Product
import com.dogo.klotinecommercemvvm.domain.useCase.products.ProductsUseCase
import com.dogo.klotinecommercemvvm.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AdminProductListViewModel @Inject constructor(
    private val productsUseCase: ProductsUseCase,
    private val savedStateHandle: SavedStateHandle
    ): ViewModel() {

    var data = savedStateHandle.get<String>("category")
    var category = Category.fromJson(data?:"")

    var productsResponse by mutableStateOf<Resource<List<Product>>?>(null)
        private set

    var productsDeleteResponse by mutableStateOf<Resource<Unit>?>(null)
        private set


    private var job: Job? = null

    fun getProducts() {
        job?.cancel()
        job = viewModelScope.launch {
            if (productsResponse !is Resource.Success) productsResponse = Resource.Loading
            productsUseCase.findBysCategory(category.id!!).collect {
                productsResponse = it
            }
        }
    }

    fun deleteProducts(id: String) = viewModelScope.launch {
        productsDeleteResponse = Resource.Loading
        val result =productsUseCase.deleteProduct(id)
        productsDeleteResponse = result
    }

    //se llama cuando la UI ya mostró el resultado para no repetir el mensaje
    fun clearDeleteResponse() {
        productsDeleteResponse = null
    }
}