package com.dogo.klotinecommercemvvm.domain.useCase.products

import com.dogo.klotinecommercemvvm.domain.repository.ProductRepository

class FindByCategoryUseCase(private val repository: ProductRepository) {

    suspend operator fun invoke(idCategory: String) = repository.findByCategory(idCategory)
}