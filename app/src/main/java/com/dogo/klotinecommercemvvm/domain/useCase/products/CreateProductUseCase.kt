package com.dogo.klotinecommercemvvm.domain.useCase.products

import com.dogo.klotinecommercemvvm.domain.model.Product
import com.dogo.klotinecommercemvvm.domain.repository.ProductRepository
import java.io.File

class CreateProductUseCase(private val repository: ProductRepository) {

    suspend operator fun invoke(product: Product, files: List<File>) = repository.create(product, files)
}