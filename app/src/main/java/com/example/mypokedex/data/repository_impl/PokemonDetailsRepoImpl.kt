package com.example.mypokedex.data.repository_impl

import com.example.mypokedex.data.api.PokeApi
import com.example.mypokedex.domain.model.PokemonDetails
import com.example.mypokedex.domain.repository.PokemonDetailRepository
import com.example.mypokedex.utils.Resource
import javax.inject.Inject

class PokemonDetailsRepoImpl @Inject constructor(
    private val pokeApi: PokeApi
): PokemonDetailRepository {
    override suspend fun getPokemonByName(name : String): Resource<PokemonDetails> {
        return try {
            val response = pokeApi.getPokemonByName(name)
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