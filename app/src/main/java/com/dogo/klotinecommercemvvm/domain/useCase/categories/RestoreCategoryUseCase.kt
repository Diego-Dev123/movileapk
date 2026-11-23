package com.dogo.klotinecommercemvvm.domain.useCase.categories

import com.dogo.klotinecommercemvvm.domain.repository.CategoriesRepository

class RestoreCategoryUseCase(private val repository: CategoriesRepository) {

    suspend operator fun invoke(id: String) = repository.restore(id)
}
