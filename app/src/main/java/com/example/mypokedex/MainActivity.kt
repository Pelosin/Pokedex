package com.example.mypokedex

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.mypokedex.ui.composables.navigation.NavigationStack
import com.example.mypokedex.ui.theme.MyPokedexTheme
import com.example.mypokedex.ui.viewmodels.PokedexViewModel
import com.example.mypokedex.ui.viewmodels.PokemonDetailsViewModel
import com.example.mypokedex.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        val pokedexViewModel: PokedexViewModel by viewModels()

        val pokemonDetailsViewModel : PokemonDetailsViewModel by viewModels()

        val isFirstOpen = sharedPrefs.getBoolean(Constants.SharedKeys.IS_FIRST_OPEN, true)

        if(isFirstOpen) {
            sharedPrefs.edit().putBoolean(Constants.SharedKeys.IS_FIRST_OPEN, false).apply()
        }

        enableEdgeToEdge()
        setContent {
            MyPokedexTheme {
                NavigationStack(
                    sharedPrefs,
                    pokedexViewModel,
                    pokemonDetailsViewModel,
                    isFirstOpen
                )
            }
        }
    }
}