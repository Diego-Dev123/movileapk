package com.dogo.klotinecommercemvvm.presentation.screens.admin.product.update.mapper

import com.dogo.klotinecommercemvvm.domain.model.Product
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.update.AdminProductUpdateState

fun AdminProductUpdateState.toProduct(): Product{
    return Product(
        name = name,
        description = description,
        idCategory = idCategory,
        price = price,
        imageToUpdate = imageToUpdate.toList(),
        image1 = image1,
        image2 = image2
    )
}
