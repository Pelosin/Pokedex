package com.example.mypokedex.data.repository_impl

import com.example.mypokedex.data.api.PokeApi
import com.example.mypokedex.domain.model.PokemonResponse
import com.example.mypokedex.domain.repository.PokemonRepository
import com.example.mypokedex.utils.Resource
import javax.inject.Inject

class PokemonRepoImpl @Inject constructor(
    private val pokeApi: PokeApi
): PokemonRepository{
    override suspend fun getAllPokemon(): Resource<PokemonResponse> {
        return try {
            val response = pokeApi.getAllPokemon()
            val result = response.body()
            if(response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Unidentified error")
        }
    }

}