package com.example.mypokedex.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.mypokedex.ui.composables.navigation.NavigationStack
import com.example.mypokedex.ui.theme.MyPokedexTheme
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        checkNotificationsPermision()

//        if(intent.action == "pokemon.intent.action.POKEMON") {
//            val pokemonId = intent.extras.getString("PokemonName")
//        }

        enableEdgeToEdge()
        setContent {
            MyPokedexTheme {
                NavigationStack()
            }
        }
    }

    val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Log.d("NotificationsTag", "Permissions Garanted")
            } else {
                Log.d("NotificationsTag", "Permissions denied")

            }


        }

    fun checkNotificationsPermision() {
        val permissionGranted = ContextCompat.checkSelfPermission(
            applicationContext,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
        if (permissionGranted) {
            Log.d("NotificationsTag", "Permissions Garanted")
        } else {
            Log.d("NotificationsTag", "Permissions denied")
            requestPermissionLauncher.launch(
                Manifest.permission.POST_NOTIFICATIONS
            )
        }

        val shouldShowRationale = ActivityCompat.shouldShowRequestPermissionRationale(
            this, Manifest.permission.POST_NOTIFICATIONS
        )
        Log.d("NotificationsTag", shouldShowRationale.toString())

        when {
            permissionGranted -> {
                Log.d("NotificationsTag", "Permission granted")
            }

            !shouldShowRationale -> {
                Log.d("NotificationsTag", "Rationale should be shown")
            }

            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.POST_NOTIFICATIONS
                )
            }
        }

    }
}