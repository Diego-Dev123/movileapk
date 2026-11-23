package com.dogo.klotinecommercemvvm.domain.useCase.products

data class ProductsUseCase(
    val createProduct: CreateProductUseCase,
    val findBysCategory: FindByCategoryUseCase,
    val findAll: FindAllUseCase,
    val updateProduct: UpdateProductUseCase,
    val updateWithImageProduct: UpdateWithImageProductUseCase,
    val deleteProduct: DeleteProductUseCase,
    val getTrash: GetTrashProductsUseCase,
    val restoreProduct: RestoreProductUseCase
)
