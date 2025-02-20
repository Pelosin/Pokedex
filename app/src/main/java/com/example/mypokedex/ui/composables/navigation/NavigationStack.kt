package com.example.mypokedex.ui.composables.navigation

import android.content.SharedPreferences
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mypokedex.ui.screens.FirstTimeScreen
import com.example.mypokedex.ui.screens.PokedexScreen
import com.example.mypokedex.ui.screens.PokemonDetailScreen
import com.example.mypokedex.ui.viewmodels.PokedexViewModel
import com.example.mypokedex.ui.viewmodels.PokemonDetailsViewModel
import com.example.mypokedex.utils.Constants

@ExperimentalMaterial3Api
@Composable
fun NavigationStack(
    sharedPreferences: SharedPreferences,
    pokedexViewModel: PokedexViewModel,
    pokemonDetailsViewModel: PokemonDetailsViewModel,
    isFirstOpen: Boolean
) {

    val navController = rememberNavController()


    val startDestination =
        if (isFirstOpen) {
            Screen.OnBoarding.route
        } else {
            Screen.Main.route
        }

    NavHost(navController, startDestination) {
        composable(route = Screen.Main.route) {
            PokedexScreen(pokedexViewModel, navController)
        }
        composable(route = Screen.OnBoarding.route) {
            FirstTimeScreen {
                navController.navigate(Screen.Main.route) {
                    popUpTo(route = Screen.OnBoarding.route) {
                        inclusive = true
                    }
                }
            }
            sharedPreferences.edit()
                .putBoolean(Constants.SharedKeys.IS_FIRST_OPEN, false).apply()
        }
        composable(route = Screen.Details.route + "/{name}") { entry ->

            entry.arguments?.getString("name")?.let {
                PokemonDetailScreen(it, pokemonDetailsViewModel, navController)
            }

        }
    }
}

sealed class Screen(val route: String) {
    data object Main : Screen("main")
    data object OnBoarding : Screen("on_boarding")
    data object Details : Screen("details")
}