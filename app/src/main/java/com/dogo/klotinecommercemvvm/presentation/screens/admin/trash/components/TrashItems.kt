package com.dogo.klotinecommercemvvm.presentation.screens.admin.trash.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dogo.klotinecommercemvvm.domain.model.TrashedCategory
import com.dogo.klotinecommercemvvm.domain.model.TrashedProduct
import com.dogo.klotinecommercemvvm.presentation.components.ItemCard
import com.dogo.klotinecommercemvvm.presentation.util.formatDeletedAt

private val RestoreColor = Color(0xFF16A34A)

@Composable
fun TrashProductItem(
    product: TrashedProduct,
    restoring: Boolean,
    onRestore: () -> Unit
) {
    ItemCard(
        image = product.image1,
        title = product.name,
        description = "Eliminado: ${formatDeletedAt(product.deletedAt)}",
        price = product.price.toString(),
        imageOnSide = true,
        actions = {
            RestoreButton(
                modifier = Modifier.padding(start = 12.dp),
                restoring = restoring,
                onClick = onRestore
            )
        }
    )
}

@Composable
fun TrashCategoryItem(
    category: TrashedCategory,
    restoring: Boolean,
    onRestore: () -> Unit
) {
    ItemCard(
        image = category.image,
        title = category.name,
        description = "Eliminada: ${formatDeletedAt(category.deletedAt)}",
        imageOnSide = true,
        actions = {
            RestoreButton(
                modifier = Modifier.padding(start = 12.dp),
                restoring = restoring,
                onClick = onRestore
            )
        }
    )
}

@Composable
private fun RestoreButton(
    modifier: Modifier = Modifier,
    restoring: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(RestoreColor.copy(alpha = 0.12f))
            .clickable(enabled = !restoring) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (restoring) {
            CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp, color = RestoreColor)
        } else {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Restaurar",
                tint = RestoreColor
            )
        }
    }
}
