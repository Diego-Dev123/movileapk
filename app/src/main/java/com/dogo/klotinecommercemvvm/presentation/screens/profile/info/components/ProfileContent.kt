package com.dogo.klotinecommercemvvm.presentation.screens.profile.info.components

import android.app.Activity
import android.content.Intent


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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.Image
import coil3.compose.AsyncImage
import com.dogo.klotinecommercemvvm.presentation.MainActivity
import com.dogo.klotinecommercemvvm.presentation.components.DefaultButton
import com.dogo.klotinecommercemvvm.presentation.components.DialogCapturePicture
import com.dogo.klotinecommercemvvm.presentation.navigation.Graph
import kotlin.apply


@Composable
fun ProfileContent(paddingValues: PaddingValues, navController: NavHostController, vm: ProfileViewModel = hiltViewModel()){


    val activity = LocalActivity.current as? Activity




    Box(
        modifier = Modifier.padding(paddingValues = paddingValues).padding(bottom = 50.dp)
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

            IconButton(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 15.dp, end = 15.dp),
                onClick = {
                    activity?.finish()
                    activity?.startActivity(Intent(activity, MainActivity::class.java))

                }) {
                Image(
                    modifier = Modifier.size(35.dp),
                    painter = painterResource(id = R.drawable.logout),
                    contentDescription = "",
                )
            }

            IconButton(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 15.dp, end = 15.dp),
            onClick = {
                vm.logout()
                activity?.finish()
                activity?.startActivity(Intent(activity, MainActivity::class.java))
            }) {
            Icon(
                modifier = Modifier.size(35.dp),
                imageVector = Icons.Default.ExitToApp,
                contentDescription = "",
                tint = Color.White
            )
        }


            if (!vm.user?.image.isNullOrBlank()){
                AsyncImage(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterHorizontally),
                    model =vm.user?.image,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                )
            } else{
                Image(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.user_image),
                    contentDescription = ""
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
                    //nombre
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = ""
                        )
                        Column(
                            modifier = Modifier.padding(horizontal = 5.dp)
                        ) {
                            Text(text = "${vm.user?.name} ${vm.user?.lastname}")
                            Text(
                                text = "Nombre del usuario",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                    }

                    //email
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = ""
                        )
                        Column(
                            modifier = Modifier.padding(horizontal = 5.dp)
                        ) {
                            Text(text = vm.user?.email ?: "")
                            Text(
                                text = "Correo electronico",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                    }
                    //telefono
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = ""
                        )
                        Column(
                            modifier = Modifier.padding(horizontal = 5.dp)
                        ) {
                            Text(text = vm.user?.phone ?: "")
                            Text(
                                text = "Telefono",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(40.dp))


                    DefaultButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Actualizar informacion",
                        onClick = {
                            navController.navigate(route = "${Graph.PROFILE}/${vm.user?.toJson()}")
                        }
                    )
                    Spacer(modifier = Modifier.height(30.dp))



                }

            }
        }
    }


}
