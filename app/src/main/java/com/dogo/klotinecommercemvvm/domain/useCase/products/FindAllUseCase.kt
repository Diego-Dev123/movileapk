package com.dogo.klotinecommercemvvm.domain.useCase.products

import com.dogo.klotinecommercemvvm.domain.repository.ProductRepository

class FindAllUseCase(private val repository: ProductRepository) {

    suspend operator fun invoke() = repository.findAll()
}