package com.dogo.klotinecommercemvvm.presentation.screens.admin.trash.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dogo.klotinecommercemvvm.domain.model.TrashedCategory
import com.dogo.klotinecommercemvvm.domain.util.Resource
import com.dogo.klotinecommercemvvm.presentation.components.SkeletonList

@Composable
fun TrashCategoriesTab(
    state: Resource<List<TrashedCategory>>,
    restoringId: String?,
    onRetry: () -> Unit,
    onRestore: (TrashedCategory) -> Unit
) {
    when (state) {
        Resource.Loading -> SkeletonList(imageOnSide = true)
        is Resource.Failure -> TrashErrorView(message = state.message, onRetry = onRetry)
        is Resource.Success -> {
            if (state.data.isEmpty()) {
                TrashEmptyView("No hay categorías eliminadas")
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(top = 8.dp, bottom = 96.dp)
                ) {
                    items(items = state.data, key = { it.id }) { category ->
                        TrashCategoryItem(
                            category = category,
                            restoring = restoringId == category.id,
                            onRestore = { onRestore(category) }
                        )
                    }
                }
            }
        }
    }
}
