package com.dogo.klotinecommercemvvm.domain.useCase.products

import com.dogo.klotinecommercemvvm.domain.repository.ProductRepository

class RestoreProductUseCase(private val repository: ProductRepository) {

    suspend operator fun invoke(id: String) = repository.restore(id)
}
