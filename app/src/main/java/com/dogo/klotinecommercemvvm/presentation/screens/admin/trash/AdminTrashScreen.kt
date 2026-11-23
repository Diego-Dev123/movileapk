package com.dogo.klotinecommercemvvm.presentation.screens.admin.trash

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.dogo.klotinecommercemvvm.domain.model.TrashedCategory
import com.dogo.klotinecommercemvvm.domain.model.TrashedProduct
import com.dogo.klotinecommercemvvm.presentation.components.DefaultTopBar
import com.dogo.klotinecommercemvvm.presentation.components.LocalAppMessenger
import com.dogo.klotinecommercemvvm.presentation.components.MessageType
import com.dogo.klotinecommercemvvm.presentation.screens.admin.trash.components.TrashCategoriesTab
import com.dogo.klotinecommercemvvm.presentation.screens.admin.trash.components.TrashProductsTab

@Composable
fun AdminTrashScreen(vm: AdminTrashViewModel = hiltViewModel()) {

    var selectedTab by remember { mutableIntStateOf(0) }
    var productToRestore by remember { mutableStateOf<TrashedProduct?>(null) }
    var categoryToRestore by remember { mutableStateOf<TrashedCategory?>(null) }
    val messenger = LocalAppMessenger.current

    LaunchedEffect(vm.message) {
        vm.message?.let {
            messenger.show(it.text, if (it.isError) MessageType.Error else MessageType.Success)
            vm.consumeMessage()
        }
    }

    Scaffold(
        topBar = { DefaultTopBar(title = "Papelera") }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TabRow(selectedTabIndex = selectedTab) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("Productos") }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("Categorías") }
                )
            }

            if (selectedTab == 0) {
                TrashProductsTab(
                    state = vm.productsTrash,
                    restoringId = vm.restoringId,
                    onRetry = { vm.loadProducts() },
                    onRestore = { productToRestore = it }
                )
            } else {
                TrashCategoriesTab(
                    state = vm.categoriesTrash,
                    restoringId = vm.restoringId,
                    onRetry = { vm.loadCategories() },
                    onRestore = { categoryToRestore = it }
                )
            }
        }
    }

    productToRestore?.let { product ->
        AlertDialog(
            onDismissRequest = { productToRestore = null },
            title = { Text("Restaurar producto") },
            text = { Text("¿Deseas restaurar \"${product.name}\"? Volverá a estar disponible en el catálogo.") },
            confirmButton = {
                TextButton(onClick = {
                    vm.restoreProduct(product)
                    productToRestore = null
                }) { Text("Restaurar") }
            },
            dismissButton = {
                TextButton(onClick = { productToRestore = null }) { Text("Cancelar") }
            }
        )
    }

    categoryToRestore?.let { category ->
        AlertDialog(
            onDismissRequest = { categoryToRestore = null },
            title = { Text("Restaurar categoría") },
            text = {
                Text("¿Deseas restaurar \"${category.name}\"? Sus productos que no fueron eliminados individualmente volverán a estar disponibles.")
            },
            confirmButton = {
                TextButton(onClick = {
                    vm.restoreCategory(category)
                    categoryToRestore = null
                }) { Text("Restaurar") }
            },
            dismissButton = {
                TextButton(onClick = { categoryToRestore = null }) { Text("Cancelar") }
            }
        )
    }
}
