package com.example.mypokedex.ui.screens

import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.mypokedex.R
import com.example.mypokedex.ui.composables.loading_image.LoadingImage
import com.example.mypokedex.ui.composables.pokedex_topbar.PokedexTopbar
import com.example.mypokedex.ui.composables.pokemon_list.PokemonList
import com.example.mypokedex.ui.utils.InputEvents
import com.example.mypokedex.ui.utils.UiState
import com.example.mypokedex.ui.viewmodels.PokedexViewModel
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@ExperimentalMaterial3Api
@Composable
fun PokedexScreen(
    navController: NavController
) {

    val pokedexViewModel: PokedexViewModel = hiltViewModel()

    val pokedexState =
        pokedexViewModel.pokedexState.collectAsStateWithLifecycle().value

    val searchQuery =
        pokedexViewModel.searchQuery.collectAsStateWithLifecycle().value

    val expandedFilter = remember { mutableStateOf(false) }
    val expandedAlpha = remember { mutableStateOf(false) }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = { PokedexTopbar(Modifier, scrollBehavior) },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        Column {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = innerPadding.calculateTopPadding(),
                        start = 10.dp,
                        end = 10.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { pokedexViewModel.onInputEvent(InputEvents.SearchQueryInput(it))},
                    placeholder = { Text("Search") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    modifier = Modifier.padding(top = 10.dp)
                )

                IconButton(
                    onClick = {
                        expandedFilter.value = true
                    },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(top = 10.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_filter_alt_24),
                        contentDescription = null,
                    )
                    DropdownMenu(
                        expanded = expandedFilter.value,
                        onDismissRequest = { expandedFilter.value = false }) {
                        Box(modifier = Modifier.size(width = 100.dp, height = 300.dp)) {
                            DropdownMenuItem(
                                { Text("fav") },
                                { pokedexViewModel.onInputEvent(InputEvents.OnFavFilter)}
                            )
                            when (pokedexState) {
                                is UiState.Success -> {
                                    //LazyColumn {
                                    //    items(pokedexState.data.size) {
                                    //        val type = pokedexState.data[it]
                                    //        DropdownMenuItem(
                                    //            { Text(type.name) },
                                    //            {}
                                    //        )
                                    //    }
                                    //}

                                }

                                is UiState.Error -> {
                                    Text(pokedexState.message)
                                }

                                UiState.Loading -> {}
                            }
                        }
                    }
                }

                IconButton(
                    onClick = {
                        expandedAlpha.value = true
                    },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(top = 10.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_sort_by_alpha_24),
                        contentDescription = null,
                    )
                    DropdownMenu(
                        expanded = expandedAlpha.value,
                        onDismissRequest = { expandedAlpha.value = false }) {
                        DropdownMenuItem(
                            { Text("A - Z") },
                            onClick = { })
                        DropdownMenuItem(
                            { Text("Z - A") },
                            onClick = { })
                    }
                }
            }

            when (pokedexState) {
                is UiState.Success -> {
                    PokemonList(
                        pokedexState.data,
                        navController
                    )
                }

                UiState.Loading -> {
                    LoadingImage(
                        rememberDrawablePainter(
                            drawable = getDrawable(
                                LocalContext.current,
                                R.drawable.pokeball_gif
                            )
                        )
                    )
                }

                is UiState.Error -> {
                    PokemonErrorScreen(pokedexState.message)
                }
            }
        }
    }

}