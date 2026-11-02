package com.dogo.klotinecommercemvvm.presentation.screens.client.category.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.dogo.klotinecommercemvvm.domain.model.Category

//@Composable
//fun ClientCategoryListItem(navController: NavHostController,category: Category){
//
//
//    Card(
//        modifier = Modifier.padding(bottom = 15.dp),
//        elevation = CardDefaults.elevatedCardElevation(
//            defaultElevation = 4.dp
//        ),
//        shape = RoundedCornerShape(10.dp)
//    ) {
//        Column() {
//
//            AsyncImage(
//                modifier = Modifier.fillMaxWidth().height(150.dp),
//                model = category.image,
//                contentDescription = "",
////                contentScale = ContentScale.Crop
//            )
//            Text(
//                modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
//                text = category.name,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black
//            )
//            Text(
//                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
//                text = category.description,
//                color = Color.Gray,
//                fontSize = 14.sp
//            )
//
//        }
//    }
//}


//-------claude
@Composable
fun ClientCategoryListItem(navController: NavHostController, category: Category) {

    Card(
        modifier = Modifier
            .padding(bottom = 14.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                    model = category.image,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                // Badge "Categorías" arriba a la izquierda
                Box(
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.TopStart)
                        .background(
                            color = Color(0xFF4F46E5),
                            shape = RoundedCornerShape(50.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = "Categorías",
                        fontSize = 11.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                }
                // Gradiente inferior
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .align(Alignment.BottomCenter)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.3f))
                            )
                        )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            ) {
                Text(
                    text = category.name,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = Color(0xFF1A1A2E),
                    letterSpacing = (-0.3).sp,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                Text(
                    text = category.description,
                    color = Color(0xFF6B7280),
                    fontSize = 13.sp,
                    lineHeight = 20.sp
                )
            }
        }
    }
}