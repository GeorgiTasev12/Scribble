package com.georgitasev.scribble

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.georgitasev.scribble.models.Routes
import com.georgitasev.scribble.ui.theme.ScribbleTheme
import com.georgitasev.scribble.ui.views.screens.DetailsScreen
import com.georgitasev.scribble.ui.views.screens.MainScreen
import com.georgitasev.scribble.viewmodels.DetailsViewModel
import com.georgitasev.scribble.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = application as ScribbleApplication

        enableEdgeToEdge()
        setContent {
            ScribbleTheme {
                ScribbleApp(application = app)
            }
        }
    }
}

@Composable
fun ScribbleApp(application: ScribbleApplication) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.MAIN_SCREEN.name,
    ) {
        composable(route = Routes.MAIN_SCREEN.name) {
            val viewModel = remember { MainViewModel(repo = application.repository) }

            MainScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = Routes.DETAILS_SCREEN.name) {
            val viewModel = remember { DetailsViewModel(repo = application.repository) }

            DetailsScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(
            route = "${Routes.DETAILS_SCREEN.name}/{noteId}",
            arguments = listOf(navArgument("noteId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val viewModel = remember { DetailsViewModel(repo = application.repository) }

            val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
            DetailsScreen(
                navController = navController,
                viewModel = viewModel,
                noteId = noteId
            )
        }
    }
}