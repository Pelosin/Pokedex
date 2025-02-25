package com.example.mypokedex.data.remote.repository

import com.example.mypokedex.data.remote.datasource.IPokemonDataSource
import com.example.mypokedex.data.utils.FunctionResponse
import com.example.mypokedex.domain.model.Evolution
import com.example.mypokedex.domain.repository.IEvolutionsRepository
import com.example.mypokedex.ui.mapper.EvolutionChainMapper
import javax.inject.Inject


//TODO: REMOVE GET BY NAME REQUEST AND GET SPRITE BY SPECIES ID
class EvolutionRepository @Inject constructor(
    private val pokemonDataSource: IPokemonDataSource
) : IEvolutionsRepository {
    override suspend fun getEvolutionChain(name : String): FunctionResponse<List<Evolution>> {
        var evolutionUrl  = ""

        when(val speciesResponse = pokemonDataSource.getSpecies(name)) {
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
                val data = evolutionResponse.data
                    ?: return FunctionResponse.Error("Empty Evolution")

                val evolutions =
                    retrieveSpriteInDetails(EvolutionChainMapper().map(data.chain))

                if(evolutions.isEmpty())
                    return FunctionResponse.Error("Empty evolutions")

                return FunctionResponse.Success(evolutions)
            }
            is FunctionResponse.Error -> {
                return FunctionResponse.Error("Unknown error")
            }
        }
    }

    override suspend fun retrieveSpriteInDetails(nameList: List<String>) : List<Evolution>{
        val evolutions = ArrayList<Evolution>()
        nameList.forEach { name ->
            when(val pokemonDetailsResponse = pokemonDataSource.getPokemonByName(name)) {
                is FunctionResponse.Success -> {
                    val pokemonDetails = pokemonDetailsResponse.data ?: return emptyList()
                    evolutions.add(Evolution(pokemonDetails.name, pokemonDetails.sprites.other.showdown.frontDefault))
                }
                is FunctionResponse.Error -> {
                    return emptyList()
                }
            }
        }
        return evolutions
    }
}