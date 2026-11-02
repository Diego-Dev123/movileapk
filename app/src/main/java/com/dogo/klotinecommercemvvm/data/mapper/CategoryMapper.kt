package com.dogo.klotinecommercemvvm.data.mapper

import com.dogo.klotinecommercemvvm.data.dataSource.local.entity.CategoryEntity
import com.dogo.klotinecommercemvvm.domain.model.Category

fun CategoryEntity.toCategory(): Category{
    return Category(
        id = id,
        name = name,
        description = description,
        image = image
    )
}

fun Category.toCategoryEntity(): CategoryEntity{
    return CategoryEntity(
        id = id ?: "",
        name = name,
        description = description,
        image = image ?: ""
    )
}