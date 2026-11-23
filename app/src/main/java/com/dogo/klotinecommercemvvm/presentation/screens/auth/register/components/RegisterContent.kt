package com.dogo.klotinecommercemvvm.presentation.screens.auth.register.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.dogo.klotinecommercemvvm.R
import com.dogo.klotinecommercemvvm.domain.util.Resource
import com.dogo.klotinecommercemvvm.presentation.components.AuthButton
import com.dogo.klotinecommercemvvm.presentation.components.DefaultTextField
import com.dogo.klotinecommercemvvm.presentation.components.JumpingCart
import com.dogo.klotinecommercemvvm.presentation.screens.auth.register.RegisterViewModel
import com.dogo.klotinecommercemvvm.ui.theme.Blue_500
import com.dogo.klotinecommercemvvm.ui.theme.Blue_800
import com.dogo.klotinecommercemvvm.ui.theme.grisOscuro

@Composable
fun RegisterContent(
    paddingValues: PaddingValues,
    onBack: () -> Unit,
    vm: RegisterViewModel = hiltViewModel()
) {
    val state = vm.state
    val errors = vm.errors
    val loading = vm.registerResponse is Resource.Loading
    val fieldShape = RoundedCornerShape(14.dp)

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.banner),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.colorMatrix(
                ColorMatrix().apply { setToScale(0.5f, 0.5f, 0.5f, 1f) }
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    modifier = Modifier
                        .padding(start = 8.dp, top = 4.dp)
                        .size(44.dp)
                        .background(Color.Black.copy(alpha = 0.3f), CircleShape),
                    onClick = onBack
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.White
                    )
                }
            }

            JumpingCart(size = 84.dp)

            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = "Crea tu cuenta",
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                fontSize = 24.sp
            )
            Text(
                text = "Ingresa tus datos para registrarte",
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(topEnd = 32.dp, topStart = 32.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.7f))
            ) {
                BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                    val minHeight = maxHeight

                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        //reparte el espacio sobrante entre los campos; si no cabe, hace scroll
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = minHeight)
                                .padding(horizontal = 24.dp, vertical = 8.dp),
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            DefaultTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = state.name,
                                onValueChange = { vm.onNameInput(it) },
                                label = "Nombre",
                                icon = Icons.Default.Person,
                                singleLine = true,
                                shape = fieldShape,
                                errorText = errors.name,
                                capitalization = KeyboardCapitalization.Words,
                                imeAction = ImeAction.Next
                            )
                            DefaultTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = state.lastName,
                                onValueChange = { vm.onLastNameInput(it) },
                                label = "Apellido",
                                icon = Icons.Outlined.Person,
                                singleLine = true,
                                shape = fieldShape,
                                errorText = errors.lastName,
                                capitalization = KeyboardCapitalization.Words,
                                imeAction = ImeAction.Next
                            )
                            DefaultTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = state.email,
                                onValueChange = { vm.onEmailInput(it) },
                                label = "Email",
                                icon = Icons.Default.Email,
                                keyboardType = KeyboardType.Email,
                                singleLine = true,
                                shape = fieldShape,
                                errorText = errors.email,
                                imeAction = ImeAction.Next
                            )
                            DefaultTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = state.phone,
                                onValueChange = { vm.onPhoneInput(it) },
                                label = "Teléfono",
                                icon = Icons.Default.Phone,
                                keyboardType = KeyboardType.Phone,
                                singleLine = true,
                                shape = fieldShape,
                                errorText = errors.phone,
                                imeAction = ImeAction.Next
                            )
                            DefaultTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = state.password,
                                onValueChange = { vm.onPasswordInput(it) },
                                label = "Contraseña",
                                icon = Icons.Default.Lock,
                                keyboardType = KeyboardType.Password,
                                hideText = true,
                                showPasswordToggle = true,
                                singleLine = true,
                                shape = fieldShape,
                                errorText = errors.password,
                                imeAction = ImeAction.Next
                            )
                            DefaultTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = state.confirmPassword,
                                onValueChange = { vm.onConfirmPasswordInput(it) },
                                label = "Confirma contraseña",
                                icon = Icons.Outlined.Lock,
                                keyboardType = KeyboardType.Password,
                                hideText = true,
                                showPasswordToggle = true,
                                singleLine = true,
                                shape = fieldShape,
                                errorText = errors.confirmPassword,
                                imeAction = ImeAction.Done,
                                onDone = { vm.register() }
                            )

                            AuthButton(text = "Registrarse", loading = loading, onClick = { vm.register() })

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(text = "¿Ya tienes cuenta?", fontSize = 14.sp, color = grisOscuro)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    modifier = Modifier.clickable { onBack() },
                                    text = "Inicia sesión",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Blue_800
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
