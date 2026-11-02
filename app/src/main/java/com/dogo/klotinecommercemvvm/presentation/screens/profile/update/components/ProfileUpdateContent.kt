package com.dogo.klotinecommercemvvm.presentation.screens.profile.update.components

import android.app.Activity
import android.content.Intent
import android.util.Log


import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.painterResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.dogo.klotinecommercemvvm.R

import com.dogo.klotinecommercemvvm.presentation.screens.profile.info.ProfileViewModel
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import com.dogo.klotinecommercemvvm.presentation.MainActivity
import com.dogo.klotinecommercemvvm.presentation.components.DefaultButton
import com.dogo.klotinecommercemvvm.presentation.components.DefaultTextField
import com.dogo.klotinecommercemvvm.presentation.components.DialogCapturePicture
import com.dogo.klotinecommercemvvm.presentation.screens.profile.update.ProfileUpdateViewModel
import kotlin.apply


@Composable
fun ProfileUpdateContent(paddingValues: PaddingValues, vm: ProfileUpdateViewModel = hiltViewModel()){


    val activity = LocalActivity.current as? Activity
    val state = vm.state

    val stateDialog = remember { mutableStateOf(false) }
    vm.resultingActivityHandler.handle()

    DialogCapturePicture(
        state = stateDialog,
        takePhoto = {vm.takePhoto()},
        pickImage = {vm.pickImage()},
    )



    Box(
        modifier = Modifier.padding(paddingValues = paddingValues)
    ){
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.profile_background),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                setToScale(0.6f, 0.6f, 0.6f, 1f)
            })
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.height(30.dp))
            if (!state.image.isNullOrBlank()){
                AsyncImage(
                    modifier = Modifier
                        .size(150.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(CircleShape)
                        .clickable {
                            stateDialog.value = true
                        },
                    model = state.image,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                )
            } else {
                Image(
                    modifier = Modifier
                        .size(150.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(CircleShape)
                        .clickable {
                            stateDialog.value = true
                        },
                    painter = painterResource(id = R.drawable.user_image),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }


            Spacer(modifier = Modifier.weight(1f))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(
                    topEnd = 40.dp,
                    topStart = 40.dp,
                ),
                colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(alpha = 0.7f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(top = 5.dp),
                        text = "ACTUALIZA TU INFORMACION",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    //nombre

                    DefaultTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.name,
                        onValueChange = {
                            vm.onNameInput(it)
                        },
                        label = "Nombre",
                        icon = Icons.Default.Person
                    )

                    //email
                    DefaultTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.lastName,
                        onValueChange = {
                            vm.onLastNameInput(it)
                        },
                        label = "Apellido",
                        icon = Icons.Outlined.Person
                    )
                    //phone
                    DefaultTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.phone,
                        onValueChange = {
                            vm.onPhoneInput(it)
                        },
                        label = "Telefono",
                        icon = Icons.Default.Phone
                    )

                    Spacer(modifier = Modifier.height(40.dp))


                    DefaultButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Confirmar",
                        onClick = {
                            vm.onUpdate()
                        }
                    )
                    Spacer(modifier = Modifier.height(30.dp))



                }

            }
        }
    }


}
