package com.example.mypokedex.ui.composables.on_boarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.mypokedex.ui.viewmodels.PokedexViewModel
import com.example.mypokedex.utils.input_events.InputEvents
import com.example.mypokedex.utils.ui_state.UiState

@Composable
fun FirstTimeScreen(
    onClick : () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Primeira vez")

        Box(
            modifier = Modifier.padding(10.dp),
        ) {
            Button(onClick = { onClick() }) {
                Text("Click me")
            }
        }
    }


}