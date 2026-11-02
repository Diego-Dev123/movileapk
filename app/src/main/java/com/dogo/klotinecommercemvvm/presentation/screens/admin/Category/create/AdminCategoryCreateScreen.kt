package com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.create

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.presentation.components.DefaultTopBar
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.create.components.AdminCategoryCreateContent
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.create.components.CreateCategory
import com.dogo.klotinecommercemvvm.ui.theme.Gris200


@Composable
fun AdminCategoryCreateScreen(navController: NavHostController){

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Nueva Categoria",
                upAvailable = true,
                navController = navController
            )
        },
        containerColor = Gris200
    ) { paddingValues ->
        AdminCategoryCreateContent(paddingValues = paddingValues)
    }
    CreateCategory()
}