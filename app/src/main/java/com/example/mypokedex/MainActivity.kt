package com.example.mypokedex

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.preference.PreferenceManager
import com.example.mypokedex.ui.composables.navigation.NavigationStack
import com.example.mypokedex.ui.theme.MyPokedexTheme
import com.example.mypokedex.ui.viewmodels.PokedexViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        val pokedexViewModel : PokedexViewModel by viewModels()

        enableEdgeToEdge()
        setContent {
            MyPokedexTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavigationStack(
                        sharedPrefs,
                        pokedexViewModel,
                        innerPadding)
                }
            }
        }
    }
}