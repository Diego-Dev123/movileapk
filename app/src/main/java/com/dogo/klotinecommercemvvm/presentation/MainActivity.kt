package com.dogo.klotinecommercemvvm.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.dogo.klotinecommercemvvm.presentation.components.AppMessenger
import com.dogo.klotinecommercemvvm.presentation.components.AppSnackbarHost
import com.dogo.klotinecommercemvvm.presentation.components.LocalAppMessenger
import com.dogo.klotinecommercemvvm.presentation.navigation.Graph
import com.dogo.klotinecommercemvvm.presentation.navigation.graph.root.RootNavGraph
import com.dogo.klotinecommercemvvm.ui.theme.KlotinEcommerceMVVMTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KlotinEcommerceMVVMTheme {
                val navController = rememberNavController()
                val startRoute = if (intent.getBooleanExtra(EXTRA_ROLE_SWITCH, false)) Graph.ROLE_SWITCH else Graph.SPLASH
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()
                val messenger = remember { AppMessenger(snackbarHostState, scope) }

                CompositionLocalProvider(LocalAppMessenger provides messenger) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Scaffold(modifier = Modifier.fillMaxSize()) {
                            RootNavGraph(navController = navController, startRoute = startRoute)
                        }
                        AppSnackbarHost(
                            hostState = snackbarHostState,
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .statusBarsPadding()
                        )
                    }
                }
            }
        }
    }

    companion object {
        const val EXTRA_ROLE_SWITCH = "role_switch"
    }
}
