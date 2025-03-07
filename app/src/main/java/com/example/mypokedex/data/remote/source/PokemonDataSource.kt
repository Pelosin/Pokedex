package com.example.mypokedex.data.remote.source

import android.util.Log
import com.example.mypokedex.data.remote.api.PokeApi
import com.example.mypokedex.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import javax.inject.Inject

class PokemonDataSource @Inject constructor(
    private val pokeApi: PokeApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : IPokemonDataSource {

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, thrwoable ->
        Log.d("thrwoable.printStackTrace()", thrwoable.stackTraceToString())
    }

    override suspend fun getAllPokemon() =
        CoroutineScope(ioDispatcher + coroutineExceptionHandler).async {
            pokeApi.getAllPokemon()
        }.await()

    override suspend fun getPokemonByName(name: String) =
        CoroutineScope(ioDispatcher + coroutineExceptionHandler).async {
            pokeApi.getPokemonByName(name)
        }.await()

    override suspend fun getEvolutionChain(id: String) =
        CoroutineScope(ioDispatcher + coroutineExceptionHandler).async {
            pokeApi.getEvolutionChain(id)
        }.await()

    override suspend fun getSpecies(name: String) =
        CoroutineScope(ioDispatcher + coroutineExceptionHandler).async {
            pokeApi.getSpecies(name)
        }.await()

    override suspend fun getTypes() =
        CoroutineScope(ioDispatcher + coroutineExceptionHandler).async {
            pokeApi.getTypes()
        }.await()
}