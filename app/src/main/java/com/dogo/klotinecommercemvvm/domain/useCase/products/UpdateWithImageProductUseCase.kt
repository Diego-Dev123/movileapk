package com.dogo.klotinecommercemvvm.domain.useCase.products

import com.dogo.klotinecommercemvvm.domain.model.Product
import com.dogo.klotinecommercemvvm.domain.repository.ProductRepository
import java.io.File

class UpdateWithImageProductUseCase(private val repository: ProductRepository) {

    suspend operator fun invoke(id: String,product: Product,files: List<File>) = repository.updateWithImage(id,product,files)
}