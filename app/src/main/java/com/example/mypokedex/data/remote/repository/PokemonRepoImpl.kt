package com.example.mypokedex.data.remote.repository_impl

import com.example.mypokedex.data.remote.api.PokeApi
import com.example.mypokedex.domain.model.PokemonResponse
import com.example.mypokedex.domain.repository.PokemonRepository
import com.example.mypokedex.utils.FunctionResponse
import javax.inject.Inject

class PokemonRepoImpl @Inject constructor(
    private val pokeApi: PokeApi
): PokemonRepository{
    override suspend fun getAllPokemon(): FunctionResponse<PokemonResponse> {
        return try {
            val response = pokeApi.getAllPokemon()
            val result = response.body()
            if(response.isSuccessful && result != null) {
                FunctionResponse.Success(result)
            } else {
                FunctionResponse.Error(response.message())
            }
        } catch (e: Exception) {
            FunctionResponse.Error(e.localizedMessage ?: "Unidentified error")
        }
    }

}