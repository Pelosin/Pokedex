package com.example.mypokedex.ui.composables

import androidx.compose.runtime.Composable
import com.example.mypokedex.ui.input_events.InputEvents
import com.example.mypokedex.ui.viewmodels.PokedexViewModel

@Composable
fun ReactToAction(viewActions: PokedexViewModel.ViewActions,
                  pokedexViewModel: PokedexViewModel) {
    when(viewActions) {
        is PokedexViewModel.ViewActions.OnPopulateList -> {
            PokemonList(viewActions.pokeList )
        }

        PokedexViewModel.ViewActions.Empty -> {
            pokedexViewModel.onInputEvent(InputEvents.CheckForFirstTime)
        }
        PokedexViewModel.ViewActions.Initial -> {
            pokedexViewModel.onInputEvent(InputEvents.OnCallApi)
        }

        PokedexViewModel.ViewActions.IsFirstTime -> {
            FirstTimeScreen { pokedexViewModel.onInputEvent(InputEvents.OnFirstTimeFinish) }
        }
    }
}