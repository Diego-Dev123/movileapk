package com.dogo.klotinecommercemvvm.domain.useCase.products

import com.dogo.klotinecommercemvvm.domain.model.Product
import com.dogo.klotinecommercemvvm.domain.repository.ProductRepository

class UpdateProductUseCase(private val repository: ProductRepository) {

    suspend operator fun invoke(id: String,product: Product) = repository.update(id,product)
}