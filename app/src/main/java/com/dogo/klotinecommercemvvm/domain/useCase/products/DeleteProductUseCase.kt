package com.dogo.klotinecommercemvvm.domain.useCase.products

import com.dogo.klotinecommercemvvm.domain.model.Product
import com.dogo.klotinecommercemvvm.domain.repository.ProductRepository

class DeleteProductUseCase(private val repository: ProductRepository) {

    suspend operator fun invoke(id: String) = repository.delete(id)
}