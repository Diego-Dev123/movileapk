package com.dogo.klotinecommercemvvm.domain.useCase.categories

import com.dogo.klotinecommercemvvm.domain.repository.CategoriesRepository

class GetTrashCategoriesUseCase(private val repository: CategoriesRepository) {

    suspend operator fun invoke() = repository.getTrash()
}
