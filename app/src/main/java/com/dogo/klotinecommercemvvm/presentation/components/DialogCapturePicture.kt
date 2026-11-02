package com.dogo.klotinecommercemvvm.presentation.components

//import android.app.AlertDialog
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.AlertDialog
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DialogCapturePicture(
    state: MutableState<Boolean>,
    takePhoto: () -> Unit,
    pickImage: () -> Unit,
) {
    Log.d("DialogCapturePicture", "state es: ${state.value}")
    if (state.value) {
        AlertDialog(
            modifier = Modifier.fillMaxWidth(),
            onDismissRequest = { state.value = false },
            containerColor = Color.White, // <-- antes era "background"
            title = {
                Text(
                    text = "Selecciona una opcion",
                    fontSize = 20.sp,
                    color = Color.Black
                )
            },
            confirmButton = { // <-- antes era "buttons"
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        modifier = Modifier.width(130.dp),
                        onClick = {
                            state.value = false
                            pickImage()
                        }
                    ) {
                        Text(text = "Galeria")
                    }
                    Button(
                        modifier = Modifier.width(130.dp),
                        onClick = {
                            state.value = false
                            takePhoto()
                        }
                    ) {
                        Text(text = "Camara")
                    }
                }
            }
        )
    }
}