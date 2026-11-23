package com.dogo.klotinecommercemvvm.presentation.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.dogo.klotinecommercemvvm.R
import com.dogo.klotinecommercemvvm.ui.theme.Blue_700

@Composable
fun DefaultTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (value: String) -> Unit,
    label: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    hideText: Boolean = false,
    singleLine: Boolean = false,
    shape: Shape = OutlinedTextFieldDefaults.shape,
    errorText: String? = null,
    imeAction: ImeAction = ImeAction.Default,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    showPasswordToggle: Boolean = false,
    onDone: (() -> Unit)? = null
    ){

    val focusManager = LocalFocusManager.current
    var visible by remember { mutableStateOf(false) }

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
        trailingIcon = if (hideText && showPasswordToggle) {
            {
                IconButton(onClick = { visible = !visible }) {
                    Icon(
                        painter = painterResource(if (visible) R.drawable.ic_visibility_off else R.drawable.ic_visibility),
                        contentDescription = if (visible) "Ocultar contraseña" else "Mostrar contraseña",
                        tint = Color.Gray
                    )
                }
            }
        } else null,
        isError = errorText != null,
        supportingText = errorText?.let { { Text(text = it) } },
        singleLine = singleLine,
        shape = shape,
        keyboardOptions = KeyboardOptions(
            capitalization = capitalization,
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) },
            onDone = {
                focusManager.clearFocus()
                onDone?.invoke()
            }
        ),
        visualTransformation = if (hideText && !visible) PasswordVisualTransformation() else VisualTransformation.None,

        colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.Black,
        unfocusedTextColor = Color.Black,
        cursorColor = Blue_700
    )
    )

}
