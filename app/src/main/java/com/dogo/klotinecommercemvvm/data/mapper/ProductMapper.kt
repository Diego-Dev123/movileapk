package com.dogo.klotinecommercemvvm.data.mapper

import com.dogo.klotinecommercemvvm.data.dataSource.local.entity.ProductEntity
import com.dogo.klotinecommercemvvm.domain.model.Product

fun ProductEntity.toProduct(): Product {
    return Product(
        id = id,
        name = name,
        description = description,
        image1 = image1,
        image2 = image2,
        price = price,
        idCategory = idCategory,
        imageToUpdate = null
    )
}

fun Product.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id ?: "",
        name = name,
        description = description,
        image1 = image1 ?: "",
        image2 = image2 ?: "",
        price = price,
        idCategory = idCategory
    )
}