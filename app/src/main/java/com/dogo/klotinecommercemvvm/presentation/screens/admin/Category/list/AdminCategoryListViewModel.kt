package com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogo.klotinecommercemvvm.domain.model.Category
import com.dogo.klotinecommercemvvm.domain.useCase.categories.CategoriesUseCase
import com.dogo.klotinecommercemvvm.domain.useCase.products.ProductsUseCase
import com.dogo.klotinecommercemvvm.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DeleteCandidate(
    val category: Category,
    val productCount: Int? = null,
    val checking: Boolean = true
)

@HiltViewModel
class AdminCategoryListViewModel @Inject constructor(
    private val categoriesUseCase: CategoriesUseCase,
    private val productsUseCase: ProductsUseCase
): ViewModel() {

    var categoriesResponse by mutableStateOf<Resource<List<Category>>?>(null)
        private set

    var deleteCategoryResponse by mutableStateOf<Resource<Unit>?>(null)
        private set

    var deleteCandidate by mutableStateOf<DeleteCandidate?>(null)
        private set

    private var job: Job? = null

    init {
        getCategories()
    }

    fun getCategories() {
        job?.cancel()
        job = viewModelScope.launch {
            if (categoriesResponse !is Resource.Success) categoriesResponse = Resource.Loading
            categoriesUseCase.getCategories().collect { data ->
                categoriesResponse = data
            }
        }
    }

    //consulta los productos reales de la categoria antes de confirmar
    fun requestDelete(category: Category) {
        val id = category.id ?: return
        deleteCandidate = DeleteCandidate(category)
        viewModelScope.launch {
            val count = when (val result = productsUseCase.findBysCategory(id).first()) {
                is Resource.Success -> result.data.size
                else -> null
            }
            if (deleteCandidate?.category?.id == id) {
                deleteCandidate = DeleteCandidate(category, count, false)
            }
        }
    }

    fun cancelDelete() {
        deleteCandidate = null
    }

    fun confirmDelete() {
        val id = deleteCandidate?.category?.id ?: return
        deleteCandidate = null
        deleteCategories(id)
    }

    fun deleteCategories(id: String) = viewModelScope.launch {
        deleteCategoryResponse = Resource.Loading
        val result = categoriesUseCase.deleteCategory(id)
        deleteCategoryResponse = result
    }

    fun clearDeleteResponse() {
        deleteCategoryResponse = null
    }
}
