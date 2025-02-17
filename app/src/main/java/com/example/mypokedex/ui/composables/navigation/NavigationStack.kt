package com.example.mypokedex.ui.composables.navigation

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mypokedex.ui.composables.on_boarding.FirstTimeScreen
import com.example.mypokedex.ui.composables.pokedex.PokedexScreen
import com.example.mypokedex.ui.viewmodels.PokedexViewModel
import com.example.mypokedex.utils.Constants

@Composable
fun NavigationStack(
    sharedPreferences: SharedPreferences,
    pokedexViewModel: PokedexViewModel
) {

    val navController = rememberNavController()

    val firstOpen =
        sharedPreferences.getBoolean(
            Constants.SharedKeys.IS_FIRST_OPEN, true
        )

    val startDestination =
        if (firstOpen) {
            Screen.OnBoarding.route
        } else {
            Screen.Main.route
        }

    NavHost(navController, startDestination) {
        composable(route = Screen.Main.route) {
            PokedexScreen(pokedexViewModel)
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
    }
}

sealed class Screen(val route: String) {
    data object Main : Screen("main")
    data object OnBoarding : Screen("on_boarding")
}