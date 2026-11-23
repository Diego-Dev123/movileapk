package com.dogo.klotinecommercemvvm.presentation.screens.client.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.dogo.klotinecommercemvvm.presentation.screens.client.detail.ClientProductDetailViewModel
import com.dogo.klotinecommercemvvm.ui.theme.Blue_500
import com.dogo.klotinecommercemvvm.ui.theme.grisOscuro
import com.optic.ecommerceappmvvm.presentation.components.DotsIndicator
import com.optic.ecommerceappmvvm.presentation.components.SliderView
import kotlinx.coroutines.delay

@Composable
fun ClientProductDetailContent(paddingValues: PaddingValues, vm: ClientProductDetailViewModel = hiltViewModel()) {

    val state = rememberPagerState(pageCount = { vm.productImages.size })
    var quantity by remember { mutableIntStateOf(0) }
    val unitPrice = vm.product.price

    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(Color(0xFFF2F2F5))
    ) {
        Column {
            SliderView(state = state, images = vm.productImages)
            Spacer(modifier = Modifier.height(8.dp))
            DotsIndicator(totalDots = vm.productImages.size, selectedIndex = state.currentPage)
        }

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 300.dp)
                .shadow(16.dp, RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)),
            shape = RoundedCornerShape(topEnd = 40.dp, topStart = 40.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = vm.product.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = grisOscuro
                    )
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = "$ $unitPrice",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Blue_500
                    )

                    SectionDivider()
                    SectionTitle("Descripción")
                    Text(
                        text = vm.product.description,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        color = Color.DarkGray
                    )

                    SectionDivider()
                    SectionTitle("Tu pedido")
                    OrderRow("Cantidad", "$quantity")
                    Spacer(modifier = Modifier.height(4.dp))
                    OrderRow("Precio por unidad", "$ $unitPrice")
                    Spacer(modifier = Modifier.height(4.dp))
                    OrderRow("Total", "$ ${unitPrice * quantity}", bold = true)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier
                            .height(48.dp)
                            .width(120.dp),
                        shape = RoundedCornerShape(24.dp),
                        color = grisOscuro,
                        shadowElevation = 6.dp
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .clickable { if (quantity > 0) quantity-- }
                                    .padding(horizontal = 12.dp),
                                text = "-",
                                fontSize = 24.sp,
                                color = Color.White
                            )
                            Text(
                                text = "$quantity",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .clickable { quantity++ }
                                    .padding(horizontal = 12.dp),
                                text = "+",
                                fontSize = 24.sp,
                                color = Color.White
                            )
                        }
                    }

                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Blue_500),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
                        onClick = {}
                    ) {
                        Text(text = "AGREGAR", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = state.settledPage) {
        delay(8500)

        var newPosition = state.settledPage + 1
        if(newPosition > vm.productImages.size - 1) newPosition = 0
        state.animateScrollToPage(newPosition)
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        modifier = Modifier.padding(bottom = 6.dp),
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = grisOscuro
    )
}

@Composable
private fun SectionDivider() {
    HorizontalDivider(
        modifier = Modifier.padding(vertical = 12.dp),
        thickness = 1.dp,
        color = Color(0xFFE3E3E8)
    )
}

@Composable
private fun OrderRow(label: String, value: String, bold: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = if (bold) 15.sp else 14.sp,
            fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal,
            color = if (bold) Color.Black else Color.DarkGray
        )
        Text(
            text = value,
            fontSize = if (bold) 15.sp else 14.sp,
            fontWeight = if (bold) FontWeight.Bold else FontWeight.SemiBold
        )
    }
}


