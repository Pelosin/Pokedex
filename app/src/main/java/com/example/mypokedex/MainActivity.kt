package com.example.mypokedex

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import androidx.preference.PreferenceManager
import com.example.mypokedex.ui.composables.navigation.NavigationStack
import com.example.mypokedex.ui.composables.pokemon_details.PokemonDetailScreen
import com.example.mypokedex.ui.theme.MyPokedexTheme
import com.example.mypokedex.ui.viewmodels.PokedexViewModel
import com.example.mypokedex.ui.viewmodels.PokemonDetailsViewModel
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

        enableEdgeToEdge()
        setContent {
            MyPokedexTheme {
                NavigationStack(
                    sharedPrefs,
                    pokedexViewModel,
                    pokemonDetailsViewModel
                )

            }
        }
    }
}