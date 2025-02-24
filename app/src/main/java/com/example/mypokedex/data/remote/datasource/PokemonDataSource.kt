package com.example.mypokedex.data.remote.datasource

import EvolutionResponse
import com.example.mypokedex.data.remote.api.PokeApi
import com.example.mypokedex.data.remote.model.SpeciesResponse
import com.example.mypokedex.data.utils.FunctionResponse
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.domain.model.PokemonDetails
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonDataSource (
    url : String
) : IPokemonDataSource {

    private val pokeApi : PokeApi = Retrofit.Builder()
    .baseUrl(url)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(PokeApi::class.java)

    override suspend fun getAllPokemon(): FunctionResponse<List<Pokemon>> {
        val response = safeApiCall { pokeApi.getAllPokemon() }

        if(response.data == null)
            return FunctionResponse.Error("Body null")

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


    private suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): FunctionResponse<T> {
        return try {
            val response = apiCall()
            val result = response.body()
            if (response.isSuccessful && result != null) {
                FunctionResponse.Success(result)
            } else {
                FunctionResponse.Error(response.message())
            }
        } catch (e: Exception) {
            FunctionResponse.Error(e.localizedMessage ?: "Unidentified error")
        }
    }

}