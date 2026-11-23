package com.dogo.klotinecommercemvvm.presentation.screens.admin.product.list

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.domain.model.Category
import com.dogo.klotinecommercemvvm.domain.model.Product
import com.dogo.klotinecommercemvvm.presentation.components.DefaultTopBar
import com.dogo.klotinecommercemvvm.presentation.navigation.screen.admin.AdminCategoryScreen
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.list.components.DeleteProducts
import com.dogo.klotinecommercemvvm.presentation.screens.admin.product.list.components.GetProducts


@Composable
fun AdminProductListScreen(
    navController: NavHostController,
    categoryParam: String?,
    vm: AdminProductListViewModel = hiltViewModel()
){
    Log.d("AdminProductListScreen", "category: $categoryParam")

    val categoryParse = Category.fromJson(categoryParam?: "").toJson()
    var productToDelete by remember { mutableStateOf<Product?>(null) }

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Productos",
                upAvailable = true,
                navController = navController
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 30.dp),
                onClick = {
                    navController.navigate(route = AdminCategoryScreen.ProductCreate.passCategory(categoryParse))
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) {paddingValues ->
        GetProducts(
            navController = navController,
            paddingValues = paddingValues,
            onDelete = { productToDelete = it }
        )
    }
    DeleteProducts()

    productToDelete?.let { product ->
        AlertDialog(
            onDismissRequest = { productToDelete = null },
            title = { Text("Eliminar producto") },
            text = {
                Text("\"${product.name}\" se moverá a la papelera y dejará de aparecer en el catálogo del cliente. Podrás restaurarlo desde la papelera.")
            },
            confirmButton = {
                TextButton(onClick = {
                    product.id?.let { vm.deleteProducts(it) }
                    productToDelete = null
                },
                    colors = ButtonDefaults.textButtonColors(contentColor = Color(0xFFDC2626))
                ) { Text("Eliminar") }
            },
            dismissButton = {
                TextButton(onClick = { productToDelete = null }) { Text("Cancelar") }
            }
        )
    }
}
