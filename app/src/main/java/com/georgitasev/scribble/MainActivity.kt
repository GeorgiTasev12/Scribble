package com.georgitasev.scribble

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.georgitasev.scribble.models.Routes
import com.georgitasev.scribble.ui.theme.ScribbleTheme
import com.georgitasev.scribble.ui.views.screens.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScribbleTheme {
                ScribbleApp()
            }
        }
    }
}

@Composable
fun ScribbleApp(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Routes.MAIN_SCREEN.name,
    ) {
        composable(route = Routes.MAIN_SCREEN.name) {
            MainScreen()
        }
    }
}