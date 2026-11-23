package com.dogo.klotinecommercemvvm.presentation.screens.client.category.list.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.domain.model.Category
import com.dogo.klotinecommercemvvm.presentation.components.ItemCard
import com.dogo.klotinecommercemvvm.presentation.navigation.screen.client.ClientCategoryScreen

@Composable
fun ClientCategoryListItem(navController: NavHostController, category: Category) {
    ItemCard(
        image = category.image,
        title = category.name,
        description = category.description,
        onClick = {
            navController.navigate(route = ClientCategoryScreen.ProductList.passCategory(category.toJson()))
        }
    )
}
