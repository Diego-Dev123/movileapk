package com.dogo.klotinecommercemvvm.presentation.screens.admin.product.update

import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.presentation.components.DefaultTopBar
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.create.components.AdminProductCreateContent
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.create.components.CreateProduct
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.update.components.AdminProductUpdateContent
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.update.components.UpdateProduct
import com.dogo.klotinecommercemvvm.ui.theme.Gris200

@Composable
fun AdminProductUpdateScreen(navController: NavHostController, productParam : String){

    Log.d("AdminProductUpdateScreen", "category: $productParam")

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Actualizar producto",
                upAvailable = true,
                navController = navController
            )
        },
        containerColor = Gris200
    ) {paddingValues ->
        AdminProductUpdateContent(paddingValues = paddingValues)
    }

    UpdateProduct()
}