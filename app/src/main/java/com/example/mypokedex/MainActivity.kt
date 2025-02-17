package com.example.mypokedex

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.preference.PreferenceManager
import com.example.mypokedex.ui.composables.ReactToAction
import com.example.mypokedex.ui.theme.MyPokedexTheme
import com.example.mypokedex.ui.viewmodels.PokedexViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)

        val pokedexViewModel : PokedexViewModel by viewModels()

        pokedexViewModel.setSharedPrefs(sharedPrefs)

        window.requestFeature(Window.FEATURE_NO_TITLE)

        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition {
            pokedexViewModel.isSplashShow.value
        }
        enableEdgeToEdge()
        setContent {
            MyPokedexTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val myAction by pokedexViewModel.viewState.collectAsStateWithLifecycle()
                    ReactToAction(myAction, pokedexViewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyPokedexTheme {

    }
}