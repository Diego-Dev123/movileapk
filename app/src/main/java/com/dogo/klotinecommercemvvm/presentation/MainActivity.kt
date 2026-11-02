package com.dogo.klotinecommercemvvm.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
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
                val navController = rememberNavController() //  declarar aquí
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    RootNavGraph(navController = navController) //  typo corregido
                }
            }
        }
    }
}


//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    KlotinEcommerceMVVMTheme {
//        Greeting("Android")
//    }
//}