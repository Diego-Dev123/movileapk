package com.dogo.klotinecommercemvvm.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.HistoricalChange
import androidx.compose.ui.text.input.KeyboardType
import com.dogo.klotinecommercemvvm.ui.theme.Blue_500
import com.dogo.klotinecommercemvvm.ui.theme.Blue_700

@Composable
fun DefaultTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (value: String) -> Unit,
    label: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text
    ){

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = {text ->
            onValueChange(text)
        },
        label = {
            Text(text = label)
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription="",
                tint = Blue_700
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType=keyboardType)

    )

}