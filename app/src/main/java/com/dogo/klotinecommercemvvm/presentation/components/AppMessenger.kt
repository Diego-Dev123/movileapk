package com.dogo.klotinecommercemvvm.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dogo.klotinecommercemvvm.R
import com.dogo.klotinecommercemvvm.ui.theme.Blue_500
import com.dogo.klotinecommercemvvm.ui.theme.Teal_500
import com.dogo.klotinecommercemvvm.ui.theme.grisOscuro
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

enum class MessageType { Success, Error, Info }

private class AppSnackbarVisuals(
    override val message: String,
    val type: MessageType
) : SnackbarVisuals {
    override val actionLabel: String? = null
    override val withDismissAction: Boolean = false
    override val duration: SnackbarDuration = SnackbarDuration.Short
}

class AppMessenger(
    private val hostState: SnackbarHostState,
    private val scope: CoroutineScope
) {
    fun show(message: String, type: MessageType = MessageType.Info) {
        if (hostState.currentSnackbarData?.visuals?.message == message) return
        scope.launch { hostState.showSnackbar(AppSnackbarVisuals(message, type)) }
    }
}

val LocalAppMessenger = staticCompositionLocalOf<AppMessenger> {
    error("AppMessenger no disponible")
}

@Composable
fun AppSnackbarHost(hostState: SnackbarHostState, modifier: Modifier = Modifier) {
    SnackbarHost(hostState = hostState, modifier = modifier) { data ->
        val type = (data.visuals as? AppSnackbarVisuals)?.type ?: MessageType.Info
        AppSnackbar(message = data.visuals.message, type = type)
    }
}

@Composable
private fun AppSnackbar(message: String, type: MessageType) {
    val accent = when (type) {
        MessageType.Success -> Teal_500
        MessageType.Error -> Color(0xFFDC2626)
        MessageType.Info -> Blue_500
    }
    Surface(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .widthIn(max = 480.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .width(5.dp)
                    .height(64.dp)
                    .background(accent)
            )
            Box(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .size(40.dp)
                    .background(accent.copy(alpha = 0.12f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.size(26.dp),
                    painter = painterResource(id = R.drawable.shopping_cart_blue),
                    contentDescription = null
                )
            }
            Text(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
                text = message,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = grisOscuro
            )
        }
    }
}
