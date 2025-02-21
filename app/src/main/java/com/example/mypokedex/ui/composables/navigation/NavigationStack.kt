package com.example.mypokedex.ui.composables.navigation

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mypokedex.ui.screens.FirstTimeScreen
import com.example.mypokedex.ui.screens.PokedexScreen
import com.example.mypokedex.ui.screens.PokemonDetailScreen
import com.example.mypokedex.ui.viewmodels.NavigationViewModel

@ExperimentalMaterial3Api
@Composable
fun NavigationStack(
) {
    val navController = rememberNavController()

    val navigationViewModel : NavigationViewModel = hiltViewModel()

    navigationViewModel.checkForRoute()

    val startDestination =
        navigationViewModel.navigationState.collectAsStateWithLifecycle().value

    Log.d("MyStartRoute", startDestination)

    NavHost(navController, startDestination) {
        composable(route = Screen.Main.route) {
            PokedexScreen(navController)
        }
        composable(route = Screen.OnBoarding.route) {
            FirstTimeScreen {
                navController.navigate(Screen.Main.route) {
                    popUpTo(route = Screen.OnBoarding.route) {
                        inclusive = true
                    }
                }
            }
        }
        composable(route = Screen.Details.route + "/{name}") { entry ->

            entry.arguments?.getString("name")?.let {
                PokemonDetailScreen(it, navController)
            }

        }
    }
}

sealed class Screen(val route: String) {
    data object Main : Screen("main")
    data object OnBoarding : Screen("on_boarding")
    data object Details : Screen("details")
}