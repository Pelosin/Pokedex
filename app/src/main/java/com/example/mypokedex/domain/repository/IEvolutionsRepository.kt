package com.example.mypokedex.domain.repository

import com.example.mypokedex.data.utils.FunctionResponse
import com.example.mypokedex.domain.model.Evolution

interface IEvolutionsRepository {
    suspend fun getEvolutionChain(name : String) : FunctionResponse<List<Evolution>>
    suspend fun retrieveSpriteInDetails(nameList: List<String>) : List<Evolution>
}