package com.example.mypokedex.data.remote.repository

import EvolutionChain
import com.example.mypokedex.data.remote.datasource.IPokemonDataSource
import com.example.mypokedex.data.utils.Constants
import com.example.mypokedex.data.utils.FunctionResponse
import com.example.mypokedex.domain.repository.IEvolutionsRepository
import javax.inject.Inject

class EvolutionRepository @Inject constructor(
    private val pokemonDataSource: IPokemonDataSource
) : IEvolutionsRepository {
    override suspend fun getEvolutionChain(id : String): FunctionResponse<EvolutionChain> {
        var evolutionUrl : String = ""
        when(val speciesResponse = pokemonDataSource.getSpecies(id)) {
            is FunctionResponse.Success -> {
                if(speciesResponse.data?.speciesEvolutionUrl?.url != null)
                    evolutionUrl = speciesResponse.data.speciesEvolutionUrl.url
            }
            is FunctionResponse.Error -> {}
        }

        if(evolutionUrl.isEmpty())
            return FunctionResponse.Error("Body null")

        val urlId = evolutionUrl.removeSuffix("/").split("/").lastOrNull()

        if(urlId.isNullOrEmpty())
            return FunctionResponse.Error("No Id")

        when(val evolutionResponse = pokemonDataSource.getEvolutionChain(urlId)) {
            is FunctionResponse.Success -> {
                return FunctionResponse.Success(evolutionResponse.data?.chain)
            }
            is FunctionResponse.Error -> {}
        }

        return FunctionResponse.Error("Teste")
    }
}