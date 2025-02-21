package com.example.mypokedex.data.remote.repository_impl

import com.example.mypokedex.data.remote.api.PokeApi
import com.example.mypokedex.domain.model.PokemonDetails
import com.example.mypokedex.domain.repository.PokemonDetailRepository
import com.example.mypokedex.utils.FunctionResponse
import javax.inject.Inject

class PokemonDetailsRepoImpl @Inject constructor(
    private val pokeApi: PokeApi
): PokemonDetailRepository {
    override suspend fun getPokemonByName(name : String): FunctionResponse<PokemonDetails> {
        return try {
            val response = pokeApi.getPokemonByName(name)
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