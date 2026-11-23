package com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dogo.klotinecommercemvvm.presentation.navigation.Graph
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.list.components.DeleteCategory
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.list.components.DeleteCategoryDialog
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.list.components.GetCategories


@Composable
fun AdminCategoryListScreen(
    navController: NavHostController,
    vm: AdminCategoryListViewModel = hiltViewModel()
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 80.dp),
                onClick = {
                    navController.navigate(route = Graph.ADMIN_CATEGORY)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) { paddingValues ->
        GetCategories(
            navController = navController,
            paddingValues = paddingValues,
            onDelete = { vm.requestDelete(it) }
        )
    }
    DeleteCategory()

    vm.deleteCandidate?.let { candidate ->
        DeleteCategoryDialog(
            candidate = candidate,
            onConfirm = { vm.confirmDelete() },
            onCancel = { vm.cancelDelete() }
        )
    }
}
