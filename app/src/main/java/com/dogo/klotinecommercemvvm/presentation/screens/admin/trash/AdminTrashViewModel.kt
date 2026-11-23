package com.dogo.klotinecommercemvvm.presentation.screens.admin.trash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogo.klotinecommercemvvm.domain.model.TrashedCategory
import com.dogo.klotinecommercemvvm.domain.model.TrashedProduct
import com.dogo.klotinecommercemvvm.domain.useCase.categories.CategoriesUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.products.ProductsUseCase
import com.dogo.klotinecommercemvvm.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TrashMessage(val text: String, val isError: Boolean = false)

@HiltViewModel
class AdminTrashViewModel @Inject constructor(
    private val productsUseCase: ProductsUseCase,
    private val categoriesUseCase: CategoriesUseCase
) : ViewModel() {

    var productsTrash by mutableStateOf<Resource<List<TrashedProduct>>>(Resource.Loading)
        private set

    var categoriesTrash by mutableStateOf<Resource<List<TrashedCategory>>>(Resource.Loading)
        private set

    //id del elemento que se está restaurando (para bloquear su botón)
    var restoringId by mutableStateOf<String?>(null)
        private set

    var message by mutableStateOf<TrashMessage?>(null)
        private set

    init {
        loadProducts()
        loadCategories()
    }

    fun loadProducts() = viewModelScope.launch {
        productsTrash = Resource.Loading
        productsTrash = productsUseCase.getTrash()
    }

    fun loadCategories() = viewModelScope.launch {
        categoriesTrash = Resource.Loading
        categoriesTrash = categoriesUseCase.getTrash()
    }

    fun restoreProduct(product: TrashedProduct) = viewModelScope.launch {
        if (restoringId != null) return@launch
        restoringId = product.id
        when (val result = productsUseCase.restoreProduct(product.id)) {
            is Resource.Success -> {
                message = TrashMessage("Producto restaurado")
                loadProducts()
            }
            is Resource.Failure -> message = TrashMessage(result.message, true)
            else -> message = TrashMessage("Error desconocido al restaurar", true)
        }
        restoringId = null
    }

    fun restoreCategory(category: TrashedCategory) = viewModelScope.launch {
        if (restoringId != null) return@launch
        restoringId = category.id
        when (val result = categoriesUseCase.restoreCategory(category.id)) {
            is Resource.Success -> {
                message = TrashMessage("Categoría restaurada")
                loadCategories()
            }
            is Resource.Failure -> message = TrashMessage(result.message, true)
            else -> message = TrashMessage("Error desconocido al restaurar", true)
        }
        restoringId = null
    }

    fun consumeMessage() {
        message = null
    }
}
