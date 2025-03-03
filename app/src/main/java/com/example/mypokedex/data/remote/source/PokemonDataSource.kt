package com.example.mypokedex.data.remote.source

import EvolutionResponse
import com.example.mypokedex.data.remote.api.PokeApi
import com.example.mypokedex.data.remote.model.SpeciesResponse
import com.example.mypokedex.data.utils.FunctionResponse
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.domain.model.PokemonDetails
import retrofit2.Response
import javax.inject.Inject

class PokemonDataSource @Inject constructor(
    private val pokeApi: PokeApi
) : IPokemonDataSource {


    override suspend fun getAllPokemon(): FunctionResponse<List<Pokemon>> {
        val response = safeApiCall { pokeApi.getAllPokemon() }

        if (response.data == null)
            return FunctionResponse.Error(response.message ?: "Body null")

        return FunctionResponse.Success(response.data.results)
    }

    override suspend fun getPokemonByName(name: String): FunctionResponse<PokemonDetails> {
        return safeApiCall { pokeApi.getPokemonByName(name) }
    }

    override suspend fun getEvolutionChain(id: String): FunctionResponse<EvolutionResponse> {
        return safeApiCall { pokeApi.getEvolutionChain(id) }
    }

    override suspend fun getSpecies(name: String): FunctionResponse<SpeciesResponse> {
        return safeApiCall { pokeApi.getSpecies(name) }
    }


    private suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): FunctionResponse<T & Any> {
        return runCatching {
            val response = apiCall()
            val result = response.body()

            if (response.isSuccessful && result != null) {
                FunctionResponse.Success(result)
            } else {
                FunctionResponse.Error(response.message())
            }
        }.getOrElse {
            FunctionResponse.Error(it.localizedMessage ?: "Unidentified error")
        }
    }

}