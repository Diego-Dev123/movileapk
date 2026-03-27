package com.dogo.klotinecommercemvvm.presentation.screens.auth.register.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.dogo.klotinecommercemvvm.R
import com.dogo.klotinecommercemvvm.presentation.components.DefaultButton
import com.dogo.klotinecommercemvvm.presentation.components.DefaultTextField
import com.dogo.klotinecommercemvvm.presentation.screens.auth.register.RegisterViewModel
import com.dogo.klotinecommercemvvm.ui.theme.Blue_500
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun RegisterContent(paddingValues: PaddingValues, vm: RegisterViewModel = hiltViewModel() ) {

    val state = vm.state

    val context = LocalContext.current

    LaunchedEffect(vm.MessageError) {
        if (vm.MessageError != "") {
            Toast.makeText(context, vm.MessageError, Toast.LENGTH_LONG).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.banner),
            contentDescription = "Imagen de fondo",
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {setToScale(0.5f,0.5f,0.5f,1f)})
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .padding(top = 20.dp),
//                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.user_image),
                contentDescription = "logo de usuario"
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                modifier = Modifier.padding(top = 7.dp),
                text = "REGISTRATE CON TUS DATOS",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 20.sp
            )
            //formulario
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(
                    topEnd = 40.dp,
                    topStart = 40.dp,
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.85f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(top = 30.dp, end = 30.dp, start = 30.dp, bottom = 30.dp)
                        .verticalScroll(rememberScrollState()
                    )
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 20.dp),
                        text = "Registro",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )

                    DefaultTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.name,
                        onValueChange = {text ->
                            vm.onNameInput(text)
                        },
                        label = "Nombre",
                        icon = Icons.Default.Person
                    )

                    DefaultTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.lastName,
                        onValueChange = {text ->
                            vm.onLastNameInput(text)
                        },
                        label = "Apellido",
                        icon = Icons.Outlined.Person
                    )
                    DefaultTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.email,
                        onValueChange = {text ->
                            vm.onEmailInput(text)
                        },
                        label = "Email",
                        icon = Icons.Default.Email,
                        keyboardType = KeyboardType.Email
                    )
                    DefaultTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.phone,
                        onValueChange = {text ->
                            vm.onPhoneInput(text)
                        },
                        label = "Telefono",
                        icon = Icons.Default.Phone,
                        keyboardType = KeyboardType.Number
                    )
                    DefaultTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.password,
                        onValueChange = {text ->
                            vm.onPasswordInput(text)
                        },
                        label = "Contrasena",
                        icon = Icons.Default.Lock,
                        keyboardType = KeyboardType.Password,
                        hideText = true

                    )
                    DefaultTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.confirmPassword,
                        onValueChange = {text ->
                            vm.onConfirmPasswordInput(text)
                        },
                        label = "Confirma contrasena",
                        icon = Icons.Outlined.Lock,
                        keyboardType = KeyboardType.Password,
                        hideText = true

                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    //boton

                    DefaultButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        onClick = {
                            vm.validateForm()
                        },
                        text = "Registrarse",
                        color = Blue_500
                    )

                }

            }

        }
    }
}