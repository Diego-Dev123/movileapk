package com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.list.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.dogo.klotinecommercemvvm.presentation.screens.admin.Category.list.DeleteCandidate

@Composable
fun DeleteCategoryDialog(
    candidate: DeleteCandidate,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    val count = candidate.productCount
    val text = when {
        candidate.checking -> "Verificando los productos de la categoría..."
        count == null -> "Si esta categoría tiene productos, dejarán de estar visibles para los clientes. ¿Deseas continuar?"
        count > 0 -> "Esta categoría contiene productos. Si la eliminas, sus productos dejarán de estar visibles para los clientes. ¿Deseas continuar?"
        else -> "¿Deseas mover esta categoría a la papelera?"
    }

    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text("Eliminar \"${candidate.category.name}\"") },
        text = { Text(text) },
        confirmButton = {
            TextButton(
                enabled = !candidate.checking,
                onClick = onConfirm,
                colors = ButtonDefaults.textButtonColors(contentColor = Color(0xFFDC2626))
            ) { Text("Eliminar") }
        },
        dismissButton = {
            TextButton(onClick = onCancel) { Text("Cancelar") }
        }
    )
}
