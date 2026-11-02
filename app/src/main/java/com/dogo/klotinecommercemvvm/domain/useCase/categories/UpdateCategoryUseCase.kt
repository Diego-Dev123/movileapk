package com.dogo.klotinecommercemvvm.domain.useCase.categories

import com.dogo.klotinecommercemvvm.domain.model.Category
import com.dogo.klotinecommercemvvm.domain.repository.CategoriesRepository
import java.io.File

class UpdateCategoryUseCase(private val repository: CategoriesRepository) {


    suspend operator fun invoke(id: String,category: Category) = repository.update(id, category)

}