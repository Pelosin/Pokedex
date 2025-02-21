package com.example.mypokedex.data.remote.datasource

import com.example.mypokedex.data.remote.api.PokeApi
import com.example.mypokedex.data.remote.model.PokemonResponse
import com.example.mypokedex.data.utils.FunctionResponse
import com.example.mypokedex.domain.model.Pokemon
import com.example.mypokedex.domain.model.PokemonDetails
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
        return try {
            val response = pokeApi.getAllPokemon()
            val result = response.body()
            if(response.isSuccessful && result != null) {
                FunctionResponse.Success(result.results)
            } else {
                FunctionResponse.Error(response.message())
            }
        } catch (e: IllegalArgumentException) {
            FunctionResponse.Error(e.localizedMessage ?: "Unidentified error")
        }
    }

    override suspend fun getPokemonByName(name : String): FunctionResponse<PokemonDetails> {
        return try {
            val response = pokeApi.getPokemonByName(name)
            val result = response.body()
            if(response.isSuccessful && result != null) {
                FunctionResponse.Success(result)
            } else {
                FunctionResponse.Error(response.message())
            }
        } catch (e: IllegalArgumentException) {
            FunctionResponse.Error(e.localizedMessage ?: "Unidentified error")
        }
    }
}